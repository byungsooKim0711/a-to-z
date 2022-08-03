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
#### Replication (복제)
- 장애를 대비하기 위한 기술로
- partition을 복제하여 다른 broker상에서 replicas(복제본)를 만들어 장애를 미리 대비한다.
- topic 생성 시 혹은 broker 에서 설정 가능하다.
  - replication factor.
### Producer
- 메시지를 생산(produce)해서 topic으로 메시지를 보내는 애플리케이션
  - Leader에만 write.
- Producer (send() → serializer → partitioner → compress → record accumulator) → Kafka
#### Partitioner
- 메시지(Record)를 topic의 어떤 partition으로 보낼지 결정
- Default Partitioner (key가 존재하는 경우)
  - Hash(Key) % #Partition
### Consumer
- topic의 메시지를 polling 하여 소비(consume)하는 애플리케이션
  - Leader로부터만 read. (kafka 2.4ver 이상부터 옵션으로 follower 로부터 데이터를 읽을 수 있도록 제공)
- 자동/수동으로 데이터를 읽은 위치를 commit 하여 다시 읽음을 방지한다.
  - __consumer_offsets topic에 consumer offset 저장하여 관리
#### Consumer-Group
- topic의 메시지를 사용하기 위해 협력하는 consumer들의 집합
- consumer group 내의 consumer 들은 협력하여 topic의 메시지를 분산 병렬 처리한다.
- group.id를 동일하게 설정

#### Rebalancing
- failure된 consumer는 consumer-group에서 제거되고, consumer-group 내 다른 consumer에게 해당 partition을 할당하여 처리한다.

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

### Record(Message) 구조
- Header(topic, partition, timestamp, etc) metadata
- Key, Value (body)
- serializer ← byte array → deserializer

#### Message Ordering
- partition이 2개 이상인 경우 모든 메시지에 대한 순서 보장이 불가능하다.
- partition을 1개로 구성하면 순서 보장이 가능하나, 처리량 저하
- 그러나 대부부의 경우, key로 구분할 수 있는 메시지들의 순서 보장이 필요한 경우가 많다.
  - 동일한 key를 사용하여 동일한 partition에 전달해주면 key 레벨의 순서를 보장 할 수 있다.
  - partition의 개수가 변경된다면..? 취약.

#### Cardinality
- key cardinality는 consumer group의 개별 consumer가 수행하는 작업량에 영향이 있다.
- key 선택이 잘못될 경우 특정 partition에 쏠릴 수 있다. (= 특정 컨슈머에서만 일하는 경우가 생긴다.)