import kotlin.system.measureTimeMillis

fun main() {

    fun detectMarkerPosition(input: String, len: Int): Int {
        val index = input.windowed(len).indexOfFirst {
            it.toSet().size == it.length
        }

        return index + len
    }

    fun part1(input: String): Int {
        return detectMarkerPosition(input, 4)
    }

    fun part2(input: String): Int {
        return detectMarkerPosition(input, 14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputRaw("Day06_test")
    checkResult(part1(testInput), 7)
    checkResult(part2(testInput), 19)

    val measured = measureTimeMillis {
        val input = readInputRaw("Day06")
        println(part1(input))
        println(part2(input))
    }

    println("Took $measured ms")
}
