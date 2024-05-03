import kotlin.random.Random

fun generateNumbers(): List<Int> {
    val numbers = mutableListOf<Int>()
    var i = 0
    while (i < 3) {
        val newNumber = when (i) {
            0 -> Random.nextInt(1, 10)
            else -> Random.nextInt(0, 10)
        }
        if (newNumber !in numbers) {
            numbers.add(newNumber)
            i++
        }
    }
    println("첫 번째는 1에서 9의 숫자를, 두세 번째는 0에서 9의 숫자를, 서로 다르게 랜덤한 순서로 뽑았어요.\n")
    return numbers
}

fun takeGuess(): List<Int> {
    println("숫자 3개를 하나씩 차례대로 입력하세요.")
    var i = 0
    val newGuess = mutableListOf<Int>()
    while (i < 3) {
        print("${i + 1}번째 숫자를 입력하세요: ")
        val guessNumber = readln()?.toIntOrNull() ?: continue // guessNumber에 입력값 정수로 받고 컨티뉴 <-표현 검토요청

        if (i == 0 && guessNumber == 0) {
            println("첫 번째는 0이 올 수 없어요. 다시 입력하세요")
            continue
        }
        if (guessNumber > 9) {
            println("9보다 큰 숫자는 올 수 없어요. 다시 입력하세요.")
            continue
        }
        if (guessNumber in newGuess) { // guessNumber가 newGuess 안에 있으면 <-표현 검토요청
            println("중복되는 숫자는 올 수 없어요. 다시 입력하세요.")
            continue
        } else {
            newGuess.add(guessNumber)
            i++
        }
    }
    return newGuess
}

fun getScore(guess: List<Int>, solution: List<Int>): Pair<Int, Int> {
    var strikeCount = 0
    var ballCount = 0
    var i = 0
    while (i < guess.size) { // i < guess사이즈 동안 <- size() 아직 잘 모름. len()랑 비교 필요.
        if (guess[i] == solution[i]) { // guess i번째 수 == solution i번째 수 이면 <-표현 검토요청
            strikeCount++
            i++
        } else if (guess[i] in solution) { // 아니면, 그리고 guess i번째 수가 solution 안에 있으면, <-표현 검토요청
            ballCount++
            i++
        } else { // 아니면
            i++ // i는 계속 증가
        }
    }
    return Pair(strikeCount, ballCount)
}

fun main() {
    val answer = generateNumbers() // answer = (generateNumbers 함수 실행한 값) <-표현 검토요청
    var tries = 0

    while (true) { // true일 동안 <-해석 검토요청
        val guess = takeGuess() // guess = (takeGuess 함수 실행한 값)
        val (strike, ball) = getScore(guess, answer) // (strike, ball) = {(guess, answer)을 getScore 함수 실행한 값} <-표현 검토요청
        println("$strike S $ball B ")

        if (strike == 3) { // 만약 strike == 3 이면
            tries++ // tries가 계속 증가하는 것을
            break // 브레이크 한다
        } else { // 아니면
            tries++ // tries는 계속 증가함
        }
    }

    println("축하합니다. ${tries}번 만에 숫자 3개의 값과 위치를 모두 맞추셨습니다.")
}