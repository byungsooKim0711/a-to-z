[toc]





# Jenkins를 이용한 CI/CD Pipeline 구축

## Docker Desktop 설치

>https://www.docker.com/products/docker-desktop/



### 간단 명령어

- docker pull ${계정/레포지토리:태그}
  ex) docker pull jenkins/jenkins:lts-jdk11
- docker run ...
- docker start ${container_id|container_name}
- docker stop ${container_id|container_name}
- docker network inspect bridge
- docker rm ${container_id/container_name}
- docker rmi ${image_id|repository:tag}
- docker login
  - /root/.docker/config.json 경로에 username:password가 base64로 인코딩됨 ...
- docker logout



## Jenkins 설치

### Docker 버전

> https://hub.docker.com/r/jenkins/jenkins

- cmd > docker pull jenkins/jenkins:lts-jdk11



### Docker로 Jenkins 실행 방법

https://github.com/jenkinsci/docker/blob/master/README.md

```shell
docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 --restart=on-failure --name jenkins-server jenkins/jenkins:lts-jdk11
```

- docker
- run
- -d (백그라운드 실행)
- -v jenkins_home:/var/jenkins_home (이미지 내 /var/jenkins_home 디렉터리를 호스트 PC 내에 마운트)
- -p 8080:8080 -p 50000:50000
- --restart=on-failure
- --name jenkins-server
- jenkins/jenkins:lts-jdk11

**로그확인**

```shell
docker logs ${container_id|container_name}
ex) docker logs jenkins-server
```

**컨테이너 접속**

```shell
docker exec -it ${container_id} ${사용할 쉘 스크립트}
ex) docker exec -it 202ceec2f709 /bin/bash
```



### Jenkins JDK 설정

- Jenkins 관리 > System Configuration > Tools > JDK



## Jenkins

> 빌드/컴파일/배포의 최소 단위를 ITEM 이라는 단위를 사용함

### Item 생성

- 새로운 Item > Item 이름 입력 > 템플릿 선택

### Git 설정

- Item 선택 > 구성 > 소스 코드 관리 > Repository, Branch 설정

### Gradle 설정

- GradleBuild Steps > Invoke Gradle script > invoke gradle (default 8.1 → 변경필요하면 Jenkins 관리 > Tools  에서 추가, 변경)
- Task
  - clean build

### Build

| 빌드 방식                                                    | 빌드 방법                                                  |
| ------------------------------------------------------------ | ---------------------------------------------------------- |
| 원격 사용                                                    | script 사용?                                               |
| 다른 프로젝트가 빌드된 이후<br />(Build after other projects are built) | 프로젝트 이름으로 검색 / 성공, 실패, ... 등에 트리거       |
| 주기적 빌드 (Build periodically)                             | cron 표현식에 의한 주기적  빌드 (변경이 없어도 하는듯?)    |
| Poll SCM                                                     | cron 표현식에 의한 주기적 확인 (push 프리거가 있을때 빌드) |
| GitHub Hook trigger for GITScm polling                       |                                                            |

- 빌드 데이터 경로
  - /var/jenkins_home/workspace/${item_name}/~



### Deploy

- ...





- Project > Configure > Build Triggers
- Build periodically -> cron job
- Poll SCM -> cron job



---

## 부록

### 리눅스 서버 설치 (docker, 강의 제공 이미지 기준)

- 리눅스 서버 이미지 다운로드

```shell
docker pull edowon0623/docker
```



- 리눅스 서버 이미지 실행

```shell
docker run --privileged --name docker-server -itd -p 10022:22 -p 18081:8080 -e container=docker -v /sys/fs/cgroup:/sys/fs/cgroup edowon0623/docker:latest /usr/sbin/init
```



- Dockerfile 내용

```shell
# 베이스 이미지 openjdk17
FROM openjdk:17 AS builder

COPY ./cicd-0.0.1-SNAPSHOT.jar cicd.jar

# 8080 컨테이너 포트 노출
EXPOSE 8080

# jar 파일 실행
ENTRYPOINT ["java","-jar","cicd.jar"]
```



- 이미지 빌드 
```shell
docker build -d -t cicd-test-server:0.1 -f Dockerfile .
```

- -d: 백그라운드
- -t: 태그명 
- -f: dockerfile의 이름 지정 (default: Dockerfile)
- .: 현재경로에서 찾음

- 위 이미지 실행
```shell
docker run -p 8888:8888 --name my-cicd-app cicd-test-server:0.1
```


## Infrastructure as Code (IaC)

> 시스템, 하드웨어 또는 인터페이스의 구성정보를 스크립트를 통해 관리 및 프로비저닝, 리소스 관리, 버전관리를 가능하도록 함

### Ansible

#### 개요

- 여러 개의 서버를 효율적으로 관리할 수 있는 환경 구성 자동화 도구

#### 설치

- docker 이미지 다운로드 (강의 제공 이미지 기준)

```shell
docker pull edowon0623/ansible
```

- ansible 서버 실행

```shell
docker run --privileged --name ansible-server -itd -p 20022:22 -p 8081:8080 -e container=docker -v /sys/fs/cgroup:/sys/fs/cgroup --cgroupns=host edowon0623/ansible /usr/sbin/init
```



`@ 다운받은 서버에서 docker 실행이 안될 때!!!!`

- /etc/docker/daemon.json 파일 추가

```json
{
    "iptables": false
}
```





- docker 네트워크 정보 확인
  - docker network inspect bridge

jenkins: 172.17.0.3
ansible: 172.17.0.2
docker-server: 172.17.0.4



- ansible이 클라이언트로 관리하는 대상은 아래 파일에서 관리함

  - /etc/ansible/hosts
  - [그룹명] 여러개 가능

  ```shell
  [devops]
  172.17.0.2
  172.17.0.4
  ```

- ansible 서버 → 클라이언트 접속 시 id/password 방식이 아닌 key 활용

  - ansible 서버에서 ssh-keygen 으로 키생성
  - 각 클라이언트 서버에 ssh-copy-id root@서버IP 키복사
  - 이후 ansible 에서 클라이언트로 ssh 접속시 패스워드 필요없음



#### 명령어

- ansible 에서 사용할 수 있는 명령어 모듈들은 https://docs.ansible.com/ansible/2.9/modules/list_of_all_modules.html 여기서 확인 가능
- ansible [groupname] -m [module]
  - ex1) ansible all -m ping (모든 그룹에 ping 모듈 실행)
  - ex2) ansible devops -m ping (devops 그룹에 ping 모듈 실행)
  - ex3) ansible all -m shell -a "free -h" (모든 그룹에 shell 명령어 실행, -a 옵션으로 파라미터 전달)
  - ex4)  ansible all -m copy -a "src=./test.txt dest=/tmp" (모든 그룹에 ./test.txt 파일을 /tmp 경로로 복사)
  - ex5) ansible all -m yum -a "name=httpd state=present" (모들 그룹에 yum 명령어 실행, -a 옵션으로 파라미터 전달)



####  Playbook

- 사용자가 원하는 내용을 미리 작성해 놓은 스크립트 파일(.yaml | .yml)
- 실행 명령어 → ansible-playbook [파일명] 
- hosts 파일은 기본적으로 /etc/ansible/hosts 파일을 참조
  - playbook 실행 시 host 파일 지정 방법
  - ansible-playbook [-i 지정할 hosts 파일경로] [playbook 파일명]

ex1)

```yaml
---
- name: Add an ansible hosts test # palybook 이름
  hosts: localhost                # group 이름 | host 이름?
  tasks:                          # 작업 목록
    - name: Add an ansible hosts  # 첫번째 작업 이름
      blockinfile:                # 첫번째 작업 모듈 
        path: /etc/ansible/hosts  # 파라미터
        block: |                  # 파라미터 (block은 파이프로 시작해야함?)
          [test-group]
          172.17.0.5
```



ex2)

```yaml
---
- name: Ansible copy example local to remote
  hosts: devops
  tasks:
    - name: Create a directory /tmp/playbook_test
      file:
        path: /tmp/playbook_test
        state: directory
        mode: 0755
    - name: Copying file with playbook
      copy:
        src: ~/test.txt
        dest: /tmp/playbook_test
        owner: root
        mode: 0644
```



### Jenkins + Ansible 연동

#### Ansible 서버정보 등록

1. Dashboard → Jenkins 관리 → System Configuration(System) →  SSH Server 추가

   1. name, hostname, username, remote directory, port, password 등

2. Item → 설정 → 빌드 후 조치 → Send build artifacts over SSH

   1. 위에서 등록한 ansible 서버 선택

   2. Transfer Set

      1. Source files 

         ```shell
         build/libs/*.jar
         ```

      2. Remove prefix

         ```shell
         build/libs
         ```

      3. Remote directory

         ```sh
         ./test
         ```

      4. Exec command

         ```shell
         cd ./test
         ansible-playbook -i hosts test-cicd-app-playbook.yaml
         ```



#### Ansible을 이용한 Docker 이미지 관리 및 생성

> **작업(task) 순서**
>
> 1. 실행중인 컨테이너를 중지
> 2. 등록된 컨테이너 삭제
> 3. 등록된 이미지 삭제
> 4. 이미지 빌드
> 5. 이미지 실행

`test-cicd-app-playbook.yaml`

```yaml
- hosts: devops
  tasks:
    ## 1. 실행중인 컨테이너 중지
    - name: "#1. Stop crruent running container with ignore error."
      command: docker stop app-cicd-test
      ignore_errors: yes
      
    ## 2. 등록된 컨테이너 삭제
    - name: "#2. Remove stopped container with ignore error."
      command: docker rm app-cicd-test
      ignore_errors: yes
    
    ## 3. 등록된 이미지 삭제
    - name: "#3. Remove current docker image with ignore error."
      command:  rmi cicd-ansible-test:0.1
      ignore_errors: yes
    
    ## 4. 신규로 이미지 빌드
    - name: "#4. Build a docker image with deployd jar file."
      command: docker build -t cicd-ansible-test:0.1 -f Dockerfile .
      args:
        chdir: /root/test
        
    ## 5. 신규 이미지 실행
    - name: "#5. Create a container using cicd-ansible-test image."
      command: docker run -d --name app-cicd-test -p 8080:8888 cicd-ansible-test:0.1
```



- 위의 이미지를 docker hub에 push를 진행하려고 한다.
  - 2개 이사의 서버에서 푸시를 다 할 필요없이 1번만 하면 된다.
  - ansible-playbook 실행 시 --limit 옵션을 주어 특정 서버에서만 작업하도록 지정할 수 있다.



- 기존의 계정명이 붙지 않는 repository 정보를 tag 옵션을 통하여 업데이트

```sh
[root@4c8298e997a0]# docker images
REPOSITORY          TAG       IMAGE ID       CREATED              SIZE
cicd-ansible-test   0.1       81dc95bbc0d0   About a minute ago   518MB
```



- 위 내용을 tag 옵션을 통해 변경

```sh
[root@4c8298e997a0]# docker tag cicd-ansible-test:0.1 byngsk/cicd-ansible-test:0.1
[root@4c8298e997a0]# docker images
REPOSITORY                 TAG       IMAGE ID       CREATED              SIZE
byngsk/cicd-ansible-test   0.1       81dc95bbc0d0   About a minute ago   518MB
cicd-ansible-test          0.1       81dc95bbc0d0   About a minute ago   518MB
```



- 아래 명령어를 통하여 docker hub 사이트에 업로드

```sh
[root@4c8298e997a0]# docker push byngsk/cicd-ansible-test:0.1
The push refers to repository [docker.io/byngsk/cicd-ansible-test]
4004b01857b8: Pushed
dc9fa3d8b576: Mounted from library/openjdk
27ee19dc88f2: Mounted from library/openjdk
c8dd97366670: Mounted from library/openjdk
0.1: digest: sha256:e0324837c51722730fbf4ca17e4b2cbef849cf2efc5adf9c85c048bb1ef9a196 size: 1166
```



- push된 이미지 정보는 https://hub.docker.com/repository/docker/byngsk/cicd-ansible-test/general 에서 확인



- docker build/push와 pull/run 분리

`#1. create-cicd-devops-image.yaml (build & push)`

```yaml
- hosts: devops
  tasks:
    #1. 도커 빌드
    - name: Create a docker image with deployed jar file
      command: docker build -t byngsk/cicd-ansible-test:0.1 -f Dockerfile .
      args:
        chdir: /root/test

    #2. 푸시
    - name: Push the image on docker-hub
      command: docker push byngsk/cicd-ansible-test:0.1

    #3. 로컬에서 삭제
    - name: Remove the docker image from the ansible server
      command: docker rmi byngsk/cicd-ansible-test:0.1
      ignore_errors: yes
```

`#2. create-cicd-devops-container.yaml (pull & run) `

```yaml
- hosts: devops
  tasks:
    ## 1. 실행중인 컨테이너 중지
    - name: "#1. Stop crruent running container with ignore error."
      command: docker stop app-cicd-test
      ignore_errors: yes

    ## 2. 등록된 컨테이너 삭제
    - name: "#2. Remove stopped container with ignore error."
      command: docker rm app-cicd-test
      ignore_errors: yes

    ## 3. 등록된 이미지 삭제
    - name: "#3. Remove current docker image with ignore error."
      command: docker rmi byngsk/cicd-ansible-test:0.1
      ignore_errors: yes

    ## 4. 허브사이트에서 이미지 땡겨오기
    - name: "#4. Pull the newest docker image from docker hub."
      command: docker pull byngsk/cicd-ansible-test:0.1

    ## 5. 이미지 실행
    - name: "#5. Create a container using cicd-ansible-test image."
      command: docker run -d --name app-cicd-test -p 8080:8888 byngsk/cicd-ansible-test:0.1
```

 `#3. jenkins 설정 변경`

- Dashboard → Item → Configuration → Send build artifacts over SSH → Exec command

```sh
cd ./test

ansible-playbook -i hosts create-cicd-devops-image.yaml --limit 172.17.0.3;
ansible-playbook -i hosts create-cicd-devops-container.yaml;
```

- Build & Push는 ansible 서버에서 진행
- Pull & Run은 hosts에 등록된 그룹 전부 진행



## Jenkins + Ansible + K8S

> 컨테이너화된 애플리케이션을 관리하기 위한 오픈 소스

### 설치오류

- Windows11 환경에서 Docker desktop에 kubenetes가 활성화되지 않을 때 참고

  ```
  https://www.c-sharpcorner.com/article/how-to-install-docker-desktop-and-troubleshoot-issues-in-windows-machine/#:~:text=Go%20to%20Docker%20and%20check,click%20and%20click%20on%20Start.&text=Another%20step%20is%20to%20verify,Hyper%2DV%20and%20containers
  ```

  

### 간단 명령어

#### Command Line

| 명령어                                                       | 설명                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| kubectl get nodes                                            | 노드 확인                                                    |
| kubectl get pods                                             | 파드 확인                                                    |
| kubectl get deployments                                      | 디플로이먼트 확인                                            |
| kubectl get services                                         | 서비스 확인                                                  |
| kubectl run sample-nginx --image=nginx --port=80             | nginx 이미지를 80포트로 실행                                 |
| kubectl describe pods sample-nginx                           | sample-nginx 파드의 상세정보 확인                            |
| kubectl delete pod/sample-nginx                              | sample-nginx 파드 삭제                                       |
| kubectl create deployment sample-nginx --image=nginx         | nignx 이미지를 디플로이먼트로 감싸서 배포?<br />deployment로 파드를 생성하면, deployment에서 유지하려고 하는 최소한의 파드 개수를 유지해준다. |
| kubectl scale deployment sample-nginx --replicas=2           | sample-nginx에서 유지하려고하는 파드 개수를 2개로 설정       |
| kubectl exec -it nginx-deployment-7fb96c846b-7f4z4 -- /bin/bash | Pod 터미널 접속                                              |
| kubectl expose deployment nginx-deployment --port=80 --type=NodePort | 80포트, 각노드에 포워딩 건 상태로 쓸 수 있도록 외부 노출,<br />kubectl get services 에서 확인 가능 |



#### Script (.yaml)

```sh
kubectl apply -f ${file_name}
ex) kubectl apply -f sample.yaml
```

**yaml 기본 포멧**

- sample.yaml

```yaml
## 
apiVersion: apps/v1
## 쿠버네티스 오브젝트 이름 (Deployment, Pod, Service, ReplicaSet)
kind: Deployment
metadata:
  name: nginx-deployment
  lables:
    app: nginx
## kind 종류에 따라 옵션들이 달라짐
spec:
  replicas: 3
  selector:
    matchLables:
      app: nginx
  ## 설치하고자 하는 pod의 내용 작성
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.25.1
        ports:
          - containerPort: 80
```



**k8s-devops-deployment.yaml**

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: cicd-deployment
spec:
  selector:
    matchLabels:
      app: cicd-devops-project
  replicas: 2

  template:
    metadata:
      labels:
        app: cicd-devops-project
    spec:
      containers:
      - name: cicd-devops-project
        image: byngsk/cicd-ansible-test:0.1
        imagePullPolicy: Always
        ports:
        - containerPort: 8080
```



**k8s-devops-service.yaml**

```yaml
apiVersion: v1
kind: Service
metadata:
  name: cicd-service
  labels:
    app: cicd-devops-project
spec:
  selector:
    app: cicd-devops-project
  type: NodePort
  ports:
    - port: 8080
      targetPort: 8888
      nodePort: 32000
```

### Kubernetes + Ansible

#### Ansible-server → Windows PC로 SSH가 안 될 경우 

- #1 윈도우 → 선택적 기능 → Open SSH 서버/클라인트 설치여부 확인하여 설치
- #2. OpenSSH Server 실행
  - #2-1 Windows PowerShell(관리자모드): `Start-Service sshd` 로 openssh server 실행
  - #2-2 또는, 서비스 실행 → OpenSSH SSH Server 실행
- #3. Windows PwoerShell 에서 `Get-NetFirewallRule -Name OpenSSH-Server-In-TCP` 를 검색하여  Enable 속성이 True인지 확인



#### 현재 문제사항

- Ansible-server Windows PC로 SSH는 되나 ansible 모듈 실행시 아래와 같이 오류 발생

```sh
[root@4c8298e997a0 ~]# ansible -i k8s/hosts kubernetes -m ping -u vlakd
[WARNING]: Invalid characters were found in group names but not replaced, use -vvvv to see details
172.27.0.1 | UNREACHABLE! => {
    "changed": false,
    "msg": "Failed to connect to the host via ssh: vlakd@172.27.0.1: Permission denied (publickey,password,keyboard-interactive).",
    "unreachable": true
}
[root@4c8298e997a0 ~]#
```

- -k 옵션으로 패스워드 직접입력 시 sshpass 를 설치하라는 메시지 출력
  - #1. wget 없어서 설치
  - #2. wget 명력어로 sshpass 설치

```sh
yum install wget

wget https://cbs.centos.org/kojifiles/packages/sshpass/1.06/8.el8/x86_64/sshpass-1.06-8.el8.x86_64.rpm 

dnf install -y ./sshpass-1.06-8.el8.x86_64.rpm
```



- hosts 파일 수정
  - [kubernetes:vars] 의 내용 추가
  - ~~ansible_remote_tmp: C:/tmp에 권한이 없다고 해서 로그인하는 계정의 경로 지정~~
  - ~~ansible_python_interpreter: /usr/bin/python 못찾는다고 해서 ansible-server에 설치된 파이썬 경로 지정~~
  - ansible_shell_type: host pc가 윈도우인 경우 powershell로 설정했었으나 오류가 있는경우 cmd로 변경
  - 

```sh
[ansible_server]
localhost

[docker_server]
172.17.0.4

[kubernetes]
172.22.128.1

[kubernetes:vars]
ansible_user=${username}
ansible_password=${password}
ansible_conntection=winrm
ansible_winrm_server_cert_validation=ignore
#ansible_remote_tmp=C:/Users/vlakd/tmp
#become_method=runas
ansible_shell_type=cmd
#ansible_python_interpreter=/usr/bin/python3
```

- 이후 오류
  - 깨진 글씨는 인코딩 해서 출력 못하나...

```sh
172.22.128.1 | FAILED! => {
    "changed": false,
    "module_stderr": "�Ű� ���� ������ Ʋ���ϴ� - ;\r\n",
    "module_stdout": "",
    "msg": "MODULE FAILURE\nSee stdout/stderr for the exact error",
    "rc": 1
}
```

- ansible_shell_type: powershell → cmd 변경 이후
  - 추가) windows host는 win_ping 모듈을 실행해야함...

```sh
[root@4c8298e997a0 k8s]$ ansible -i hosts kubernetes -m win_ping
172.22.128.1 | SUCCESS => {
    "changed": false,
    "ping": "pong"
}
```



#### Ansible + K8S

**k8s deployment 등록**

- `172.17.0.3(ansible-server) → k8s-cicd-deployment-playbook.yaml`

  - host pc가 windows인 경우 command 대신 win_command 명령어를 사용해야한다.

  ```yaml
  - name: Create pods using deployment
    hosts: kubernetes
    tasks:
    - name: delete the previous deployment
      win_command: kubectl delete deployment.apps/cicd-deployment
      ignore_errors: yes
  
    - name: create a deployment
      win_command: kubectl apply -f cicd-devops-deployment.yaml
  ```

- `host pc(kubernetes 가 설치된) → cicd-devops-deployment.yaml`

  ```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: cicd-deployment
  spec:
    selector:
      matchLabels:
        app: cicd-devops-project
    replicas: 2
  
    template:
      metadata:
        labels:
          app: cicd-devops-project
      spec:
        containers:
        - name: cicd-devops-project
          image: byngsk/cicd-ansible-test:0.1
          imagePullPolicy: Always
          ports:
          - containerPort: 8080
  ```

- ansible-server 에서 `ansible-playbook -i hosts k8s-cicd-deployment-playbook.yaml` 명령어 실행
- k8s 가 설치되어있는 host pc 에서 `kubectl get deployments`로 등록이 되어있는지 확인



**k8s service 등록**

- `172.17.0.3(ansible-server) → k8s-cicd-service-playbook.yaml`

  ```yaml
  - name: create service for deployment
    hosts: kubernetes
    tasks:
    - name: create a service
      win_command: kubectl apply -f cicd-devops-service.yaml
  ```

  

- `host pc(kubernetes가 설치된) → cicd-devops-service.yaml`

  ```yaml
  apiVersion: v1
  kind: Service
  metadata:
    name: cicd-service
    labels:
      app: cicd-devops-project
  spec:
    selector:
      app: cicd-devops-project
    type: NodePort
    ports:
      - port: 8888
        targetPort: 8888
        nodePort: 32000
  ```

  - ansible-server에서 `ansible-playbook -i hosts k8s-cicd-service-playbook.yaml` 명령어 실행
  - k8s 가 설치되어있는 host pc 에서 `kubectl get services`로 등록이 되어있는지 확인
    - 브라우저에서 localhost:32000/owner 를 접속하여 api 호출여부 확인



#### Jenkins + Ansible + K8S

- 수동으로 했던 내용들을 Jenkins의 빌드 후 조치 → Send build artifacts over SSH → Exec command 에 입력

  ```shell
  ansible-playbook -i hosts k8s-cicd-deployment-playbook.yaml;
  ansible-playbook -i hosts k8s-cicd-service-playbook.yaml
  ```

  - Build Suceess 확인
  - kubectl get pods, kubectl get deployments, kubectl get services 명령어 및 브라우저에서 API 호출여부 확인



**CI(Continuous Integration) Jobs**

- Git Pull
- Create a docker image
- Push the image to the docker-hub
- Remove the image from the local





**CD(Confinuous Deployment) Jobs**

- Create a deployment
- Create a service