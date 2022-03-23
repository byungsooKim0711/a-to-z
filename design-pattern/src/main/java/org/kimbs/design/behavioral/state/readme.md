### State pattern
#### 상태에 따라 행동이 달라지는 패턴
- 상태에 특화된 행동들을 분리해 낼 수 있으며, 새로운 행동을 추가하더라도 다른 행동에 영향을 주지 않는다.


#### example
- 해당 예제는 Entity 에 state pattern 을 적용해보려고 한것인데...
(현재 entity 의 상태에따라서 외부 api 호출하는 경우들이 있음...)
- 그래서 @Enumerated(string) 부분에 상태별로 동작하는 것들을 넣으면 되지않을까 싶었다...
- 궁금한것은...
  1. 현업에서 예제 소스처럼 state pattern에 enum 을 사용하는지 (or entity 에 state pattern 을 적용하는지)
  2. 아니면 entity 같은경우에 stateMachine 을 사용하나..?
  3. ...


| 상태 패턴 구성요소 | 클래스 / 인터페이스                                          |
| ------------------ | ------------------------------------------------------------ |
| State              | TemplateStatus                                               |
| ConcreteState      | RegisteredStatus<br />RejectStatus<br />ApprovalStatus<br />RequestStatus |
| Context            | Template                                                     |
| Client             | TemplateService                                              |

