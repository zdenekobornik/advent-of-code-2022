enum class Move(val score: Int) {
    ROCK(1), PAPER(2), SCISSORS(3);

    companion object {
        fun parse(value: String): Move {
            return when (value) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSORS
                else -> throw IllegalStateException("Unknown move.")
            }
        }
    }
}
fun main() {


    fun part1(input: List<String>): Int {
        return input
            .map { it.split(' ', limit = 2).map(Move::parse) }
            .map { (oponnent, me) ->
                /*
                    A - rock - 65
                    B - Paper - 66
                    C - Scissors - 67

                    X - rock - 88
                    Y - Paper - 89
                    Z - Scissors - 90
                     */

                var score = me.score

                score += when {
                    (oponnent == Move.ROCK && me == Move.PAPER) || (oponnent == Move.PAPER && me == Move.SCISSORS) || (oponnent == Move.SCISSORS && me == Move.ROCK) -> 6
                    oponnent == me -> 3
                    else -> 0
                }


                score
            }.sum()
    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(' ', limit = 2) }
            .map { (oponnent, need) ->
                /*
                    A - rock
                    B - Paper
                    C - Scissors

                    X - rock
                    Y - Paper
                    Z - Scissors


                    Need
                    X - Lose
                    Y - Draw
                    Z - Win
                     */

                val me = when (oponnent) {
                    "A" -> when (need) {
                        "X" -> "C"
                        "Y" -> "A"
                        "Z" -> "B"
                        else -> throw IllegalStateException("Unknown move.")
                    }
                    "B" -> when (need) {
                        "X" -> "A"
                        "Y" -> "B"
                        "Z" -> "C"
                        else -> throw IllegalStateException("Unknown move.")
                    }
                    "C" -> when (need) {
                        "X" -> "B"
                        "Y" -> "C"
                        "Z" -> "A"
                        else -> throw IllegalStateException("Unknown move.")
                    }
                    else -> throw IllegalStateException("Unknown move.")
                }

                var score = when (me) {
                    "A" -> 1
                    "B" -> 2
                    "C" -> 3
                    else -> throw IllegalStateException("Unknown move.")
                }

                score += when {
                    (oponnent == "A" && me == "B") || (oponnent == "B" && me == "C") || (oponnent == "C" && me == "A") -> 6
                    oponnent == me -> 3
                    else -> 0
                }


                score
            }.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
