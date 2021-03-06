# And_WeatherSample


## TIL

kotlin에서는 if문 없는 프로그래밍을 추구하는 함수형 프로그래밍 답게 상태관리를 줄여주는 함수를 제공

![](https://miro.medium.com/max/761/1*pLNnrvgvmG6Mdi0Yw3mdPQ.png)

대표적으로 `let`, `apply`, `run`, `also`

#### let
좌측의 함수나 객체의 `결과값`을 넘겨받는다.
이름을 지정하지 않으면 it.
?가 있다면 좌측 객체나 함수의 결과가 null일 경우, let{}안의 코드는 실행되지 않는다.
```swift
  "123".let { 
      it + "45"
  }.let {
      it + "67"
  }.let {
      print(it)
  }
```

#### apply
좌측의 `함수`나 `객체`를 넘겨받는다.
apply()안에서 객체의 값을 변경하고 반환한다.
넘겨 받는 객체는 this.

```swift
val changedUser = User("1", "김승진").apply {
    this.id = "2"
    this.name = "오오오"
}

println(changedUser)
//User(id=2, name=오오오)
    
```
