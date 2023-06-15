# Jenkins를 이용한 CI/CD Pipeline 구축

## Docker Desktop 설치

>https://www.docker.com/products/docker-desktop/



### 간단 명령어

- docker pull ${계정/레포지토리:태그}
  ex) docker pull jenkins/jenkins:lts-jdk11

- docker run ...
- docker start ${container_id|container_name}
- docker stop ${container_id|container_name}



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

