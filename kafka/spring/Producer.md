## Kafka Producer

### 1. KafkaTemplate
   1. ProducerFactory 클래스를 이용하여 생성
      - 트랜잭션을 사용하지 않는 경우, Singleton 으로 생성된다.
      - flush()를 사용할 경우 같은 producer를 사용하는 다른 쓰레드에서 지연현상이 발생할 수 있다.
   2. 기본적으로 비동기처리
      - 동기로 처리할 수 있으나, 사용하지 않는게 좋음
   3. KafkaTemplate Listener
      - 2.5 이전: ListenableFutureCallback
        - onFailure 메서드에서 어떤 메시지가 실패했는지 알 수 없음
      - 2.5 이후: KafkaSendCallback
        - onFailure 메서드에서 어던 메시지가 실패했는지 알 수 있음

### 2. RoutingKafkaTemplate
   1. 2.5 부터 지원
   2. 전송하는 토픽별로 옵션(ProducerFactory)을 다르게 설정할 수 있다.
      - 토픽명은 정규식으로 표현 가능
   3. transaction, execute, flush, metric 커맨드 미지원

### 3. ReplyingKafkaTemplate
   1. 2.1.3 부터 지원
   2. Consumer가 특정 데이터를 전달 받았는지 여부를 확인할 수 있다.
   3. 3개의 Header가 기본으로 정의된다.
      - KafkaHeaders.CORRELATION_ID (요청과 응답을 연결하기 위한 ID)
      - KafkaHeaders.REPLY_TOPIC (응답 토픽)
      - KafkaHeaders.REPLY_PARTITION (응답 토픽의 파티션)

