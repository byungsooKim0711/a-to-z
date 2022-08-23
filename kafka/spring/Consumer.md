`## Kafka Consumer

### Message Listener
#### Record
- MessageListener
  - Auto commit
- AcknowledgingMessageListener
  - Manual commit
- ConsumerAwareMessageListener
  - Consumer 객체 활용
- AcknowledgingConsumerAwareMessageListener
  - Manual commit + Consumer 객체 사용
#### Batch
- BatchMessageListener
    - Auto commit
- BatchAcknowledgingMessageListener
    - Manual commit
- BatchConsumerAwareMessageListener
    - Consumer 객체 활용
- BatchAcknowledgingConsumerAwareMessageListener
    - Manual commit + Consumer 객체 사용
### @KafkaListener
- clientIdPrefix, autoStartup, concurrency, topicPartitions 등 쉽게 설정 가능
- 메타데이터
  - offset, recevied_message_key, received_topic, received_partition_id, received_timestamp, timestamp_type
### Payload Validator
- 2.2 이후부터 쉽게 설정 가능
- KafkaListenerEndpointRegistrar 에 등록하여 사용가능
- LocalValidatorFactoryBean을 이용하면 javax.validation(JSR-303)에 정의된 @NotEmpty, @Min ... 등을 사용하여 유효성 체크가 가능하다.
### Retrying Deliveries

### AckMode
| 이름             | 설명                                                         |
| ---------------- | ------------------------------------------------------------ |
| RECORD           | 레코드를 처리한 후 리스너가 반환될 때 커밋                   |
| BATCH            | poll() 메서드로 호출된 레코드가 모두 처리된 이후 커밋 (default) |
| TIME             | ackTime 만큼 지난 이후에 커밋<br />시간 간격을 선언하는 ackTme 옵션을 설정해야 한다. |
| COUNT            | ackCount 로 설정된 개수만큼 레코드가 처리된 이후에 커밋<br />레코드 개수를 선언하는 ackcount 옵션을 설정해야 한다. |
| COUNT_TIME       | COUNT, TIME 중 맞는 조건이 나오면 커밋                       |
| MANUAL           | Acknowledgement.acknowledge() 가 호출되면 다음번 poll() 메서드 호출 시 커밋 |
| MANUAL_IMMEDIATE | Acknowledgement.acknowledge() 가 호출되면 커밋               |

