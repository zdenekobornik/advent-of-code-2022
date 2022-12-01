
fun main() {
    fun part1(input: String): Int {
        return input.split("\n\n").maxOf { it.split("\n").sumOf(String::toInt) }
    }

    fun part2(input: String): Int {
        return input.split("\n\n")
            .map { it.split("\n").sumOf(String::toInt) }
            .sortedDescending()
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputRaw("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInputRaw("Day01")
    println(part1(input))
    println(part2(input))
}
