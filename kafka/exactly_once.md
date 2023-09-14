## Exactly once semantics ('정확히 한 번')

> 카프카에서 '정확히 한 번'의 의미 구조는 `멱등적(Idempotent) 프로듀서` + `트랜잭션` 조합으로 이루어진다.
>
> '멱등적' 이란? 
>
> - 동일한 작업을 여러 번 실행하도 한 번 실행한 것과 결과가 같은 것



### Idempotent Producer

> 설정방법: 프로듀서 설정에서 `enable.idempotence=true` 추가

- Producer ID + Sequence ID + Topic + Partition 을 조합하여 메시지마다의 고유한 식별자 생성
- 브로커는 모든 파티션들에 쓰여진 마지막 N개 메시지들을 추적하기 위해 위 식별자 사용
- 추적하는 시퀀스 넘버의 수를 제한하려면 프로듀서 옵션의
  - `max.in.flights.requests.per.connection` 설정을 조절하면 된다. (default: 5)
- 브로커가 예상보다 높은 시퀀스를 받게된다면,
  - out of order sequence number 에러 발생
  - 7다음 20의 시퀀스를 받았다면, 8~19번 메시지까지 뭔가가 발생했을 것 (유실 등)

#### 한계

- 프로듀서 자체의 재시도 메커니즘에 의한 중복을 방지할 뿐 그 이상은 하지 않는다.





