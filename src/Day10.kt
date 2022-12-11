fun main() {
    fun generateValues(testInput: List<String>): List<Int> {
        return testInput.flatMap { line ->
            when (line) {
                "noop" -> listOf(0)
                else -> listOf(0, line.substring(5).toInt())
            }
        }.runningFold(1) { x, op -> x + op }
    }

    fun part1(testInput: List<String>): Int {
        val values = generateValues(testInput)
        return listOf(20, 60, 100, 140, 180, 220).sumOf { it * values[it - 1] }
    }

    fun part2(testInput: List<String>) {
            (1..240).zip(generateValues(testInput))
            .forEach { (cycle, x) ->
                val sprite = x until (x + 3)
                val position = cycle % 40

                print(if (position in sprite) '▓' else '░')

                if (position == 0) println()
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    checkResult(part1(testInput), 13140)
    part2(testInput)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)
}
