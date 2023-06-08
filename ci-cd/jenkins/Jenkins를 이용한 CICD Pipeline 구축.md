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



## Jenkins 프로젝트

> 빌드/컴파일/배포의 최소 단위를 ITEM 이라는 단위를 사용함

### Item 생성

- 새로운 Item > Item 이름 입력 > 템플릿 선택

### Git, Gradle설정

- 



