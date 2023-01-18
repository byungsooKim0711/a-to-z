## 변수 선언
### 기본
```kotlin
fun main() {
    val number = 10
    var language = "korean"
    val secondNumber: Int = 20
    
    println("number: $number")
}
```
- var: 초기화 이후에 `수정 가능`
- val: 초기화 이후에 `수정 불가능`

## 함수선언
### 기본
```kotlin
fun sum(a: Int, b: Int): Int {
    val sum = a + b
    return sum
}
```

- fun 함수임을 명시
- sum 함수 명
- (a: Int, b: Int): Int 2개의 파라미터, a, b는 Int 타입, 함수의 반환형은 Int 타입


```kotlin
fun sum(a: Int, b: Int): Int = a + b
fun sum(a: Int, b: Int) = a + b
```
- 한 줄로 표현가능한 경우 위 표현식으로 사용 가능

### 반환값이 없는 함수
```kotlin
fun log(message: String): Unit {
    println(message);
}

fun log(message: String) {
    println(message);
}
```
- 위 2개의 함수는 반환값이 없는 함수로 반환 자료형을 생략하더라도 실제 반환값은 Unit 이다.

### 초기화된 매개변수
```kotlin
fun main(args: Array<String>) {
    log(a = 3)
    log(a = 100, message = "kbs1")
    log(message = "kbs2", a = 100)
}

fun log(message: String = "default message.", a: Int): Unit {
    println("$message - $a");
}
```
- 매개변수에 초기화를 지정할 수 있다.
- 함수 호출 시 특정 매개변수를 지정하여 인자를 넘길 수 있다.


### 가변인자 함수
```kotlin
fun main(args: Array<String>) {
    log(1, 2, 3, 4);
    log(10, 20);
}

fun log(vararg numbers: Int): Unit {
    for (number in numbers) {
        println("number: $number")
    }
}
```
- vararg 키워드로 가변이자를 표현할 수 있다. (java의 String... args 느낌)