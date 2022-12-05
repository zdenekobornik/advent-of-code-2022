fun main() {


    fun part1(input: List<String>): Int {
        return input.sumOf {
            val (left, right) = it.chunked(it.length / 2)
            val char = left.first { right.contains(it) }
            if (char in 'a' .. 'z') {
                char.code - 96
            } else {
                char.code - 38
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .sumOf { chunk ->
                val char = chunk
                    .map { it.toSet() }
                    .reduce { acc, chars ->
                        acc.intersect(chars)
                    }
                    .first()

                if (char.isLowerCase()) {
                    (char - 96).code
                } else {
                    (char - 38).code
                }
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    checkResult(part1(testInput), 157)
    checkResult(part2(testInput), 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
