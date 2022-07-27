# Apache Kafka 기본 개념
## 주요 구성 요소
### Topic
- kafka 안에서 메시지가 저장되는 논리적인 장소
#### Partition
- 모든 topic은 1개 이상의 partition으로 구성된다.
#### Segment
- 메시지가 저장되는 실제 물리적인 file로 segment file이 지정된 크기보다 크거나 지정된 기간보다 오래되면 새 파일이 열리고 메시지는 새 파일에 추가된다.
- log.segment.bytes(default: 1GB)
- log.roll.hours(default: 168 hours)
#### Offset
- offset은 하나의 partition에서만 의미랄 갖는다.
- 즉, partition 별로 offset이 존재한다.
### Producer
- 메시지를 생산(produce)해서 topic으로 메시지를 보내는 애플리케이션
- Producer (send() → serializer → partitioner → compress → record accumulator) → Kafka
#### Partitioner
- 메시지(Record)를 topic의 어떤 partition으로 보낼지 결정
- Default Partitioner (key가 존재하는 경우)
  - Hash(Key) % #Partition
### Consumer
- topic의 메시지를 polling 하여 소비(consume)하는 애플리케이션
#### Consumer-Group
- topic의 메시지를 사용하기 위해 협력하는 consumer들의 집합
- consumer group 내의 consumer 들은 협력하여 topic의 메시지를 분산 병렬 처리한다.

### Broker
- topic과 partition을 유지 및 관리, read/write 등
- topic 내의 partition들을 분산, 유지 및 관리
- broker의 구분은 id로 구분한다. (숫자)
#### Kafka Cluster
- 여러개의 broker 들로 구성된다.
- client는 특정 broker에 연결하면 전체 클러스터에 연결된다.
- 최소 3대 이상의 broker를 하나의 cluster로 구성해야 한다.
#### Bootstrap Server
- cluster로 구성된 모든 브로커의 리스트
### Zookeeper
- broker의 목록, 설정등을 관리하는 소프트웨어
- 변경사항(topic/broker 생성/제거) 에 대해서 kafka에 알림
- zookeeper 제거 예정
- 홀수의 서버로 구성하는게 특징이다.
  - 최소 3, 권장 5
  - 과반수 정책에 의한 설계 (Quorum 알고리즘 기반)
  - 짝수로 구성해도 상관없다??. 3대 설치하나, 4대 설치하나 1대의 down 밖에 버티지 못하므로 비효율적일 뿐
- 분산 작업을 제어하기 위한 Tree 형태의 저장소
- Leader, Follower로 구성

### Recode(Message) 구조
- Header(topic, partition, timestamp, etc) metadata
- Key, Value (body)
- serializer ← byte array → deserializer