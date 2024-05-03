# Kotlin문법 심화 과제 - 숫자 야구 게임




  > **Goal : 숫자 야구 게임 만들기**
  > 
  > - 필수 구현 기능을 구현해보시고, 선택 구현 기능도 도전해보세요!
  > - 지금까지 배운 Kotlin 문법을 응용해서 숫자 야구 게임을 만들어봅니다.

1. 필수 구현 기능
- [x]  입력과 출력
   - [x]  입력
       - [x]  서로 다른 3자리 수
       - [ ]  게임 시작, 기록 보기, 종료를 구분하는 수 입력
           - [x]  필수 구현에서는 실행 시, 바로 게임 시작
           - [ ]  선택 구현에서 시작, 기록, 종료 구분
   - [x]  출력
       - [x]  입력한 수에 대한 결과값을 “볼, 스트라이크, Nothing”으로 표시
- [x]  요구사항
    - [x]  1에서 9까지의 서로 다른 임의의 수 3개를 정하고 맞추는 게임입니다
    - [x]  정답은 랜덤으로 만듭니다.(1에서 9까지의 서로 다른 임의의 수 3자리)
    - [x]  상세
        - [x]  정답을 맞추기 위해 3자리수를 입력하고 힌트를 받습니다
            - [x]  힌트는 야구용어인 **볼**과 **스트라이크**입니다.
            - [x]  같은 자리에 같은 숫자가 있는 경우 **스트라이크**, 다른 자리에 숫자가 있는 경우 **볼**입니다.
                - ex) 정답 : 456 인 경우
                    - 435를 입력한 경우 → 1스트라이크 1볼
                    - 357를 입력한 경우 → 1스트라이크
                    - 678를 입력한 경우 → 1볼
                    - 123를 입력한 경우 → Nothing
            - [x]  만약 올바르지 않은 입력값에 대해서는 오류 문구를 보여주세요.
            - [x]  3자리 숫자가 정답과 같은 경우 게임이 종료됩니다.

2. 선택 구현 기능
    - 해당사항 없음

```java
구현 기능 요약) 
랜덤한 각기 다른 정수 3개를 생성. 랜덤한 각기 다른 정수 3개를 입력.
숫자야구 롤을 적용하여 두 수를 비교하고, 점수를 산정함.
```

## 코드 상세설명

기준이 되는 정답숫자를 생성하는 함수 generateNumbers 설정
```
fun generateNumbers(): List<Int> {
    val numbers = mutableListOf<Int>()
```
숫자의 개수는 3개로 설정하고, 첫 번째 수에는 0이 오지 않게 1~9로 제한
```
    var i = 0
    while (i < 3) {
        val newNumber = when (i) {
            0 -> Random.nextInt(1, 10)
            else -> Random.nextInt(0, 10)
        }
```
비교할 숫자를 readln으로 받음
```

    while (i < 3) {
        print("${i + 1}번째 숫자를 입력하세요: ")
        val guessNumber = readln()?.toIntOrNull() ?: continue 

```
세부적인 조건 제한.<br/>
첫번째 수에 0이 오지 못하게/ 9가 넘는 두자리수 오지 못하게/ 같은 수를 중복으로 입력하지 못하게
```
        if (i == 0 && guessNumber == 0) {
            println("첫 번째는 0이 올 수 없어요. 다시 입력하세요")
            continue
        }
        if (guessNumber > 9) {
            println("9보다 큰 숫자는 올 수 없어요. 다시 입력하세요.")
            continue
        }
        if (guessNumber in newGuess) { 
            println("중복되는 숫자는 올 수 없어요. 다시 입력하세요.")
            continue
        } else {
            newGuess.add(guessNumber)
            i++
        }
    }
    return newGuess
}
```
점수 산정 방식을 수행하는 함수 설정<br/>
strikeCount와 ballCount로 계산 진행.
```

    fun getScore(guess: List<Int>, solution: List<Int>): Pair<Int, Int> {
    var strikeCount = 0
    var ballCount = 0
    var i = 0
    while (i < guess.size) { 
        if (guess[i] == solution[i]) { 
            strikeCount++
            i++
        } else if (guess[i] in solution) { 
            ballCount++
            i++
        } else { 
            i++ 
        }
    }
    return Pair(strikeCount, ballCount)
}


```
메인 함수에서 정답숫자 생성하는 함수, 비교숫자 입력하는 함수, 그리고 두 숫자를 비교하는 점수산정 함수를 실행<br/>
3스트라이크일 경우, 함수 진행이 break 되고 멘트가 출력됨
```

fun main() {
    val answer = generateNumbers() 
    var tries = 0

    while (true) { 
        val guess = takeGuess() 
        val (strike, ball) = getScore(guess, answer) 
        println("$strike S $ball B ")

        if (strike == 3) { 
            tries++ 
            break 
        } else { 
            tries++ 
        }
    }

    println("축하합니다. ${tries}번 만에 숫자 3개의 값과 위치를 모두 맞추셨습니다.")
}

```