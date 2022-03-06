### Singleton Pattern
- 싱글톤 패턴은 인스턴스가 오직 하나만 생성되는 것을 보장하고 어디에서든 이 인스턴스에 접근할 수 있도록 하는 디자인패턴
  - cf. 원래 싱글턴이라는 단어는 `단 하나의 원소만을 가진 집합`이라는 수학 이론에서 유래


#### Diagram

#### 사용 방법
- static 변수에 초기화
  - 장점:
  - 단점:
- static block 초기화
  - 장점:
  - 단점:
- synchronized 키워드 사용
  - 장점:
  - 단점:
- Double-checked locking (volatile 사용)
  - 장점:
  - 단점:
- Bill pugh Solution (Holder 사용)
  - 장점:
  - 단점:
- Enum 사용
  - 장점: 
  - 단점: