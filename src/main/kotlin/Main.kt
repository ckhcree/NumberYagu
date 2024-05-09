import kotlin.random.Random



fun main() {
    val baseballgame = Baseballgame() // Baseball 클래스 생성해서 활용
    val answer = baseballgame.generateNumbers()
    var tries = 0

    while (true) {
        val guess = baseballgame.takeGuess()
        val (strike, ball) = baseballgame.getScore(guess, answer)
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