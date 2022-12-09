import java.lang.Integer.max

fun main() {
    fun <T> Iterable<T>.countWhile(predicate: (T) -> Boolean): Int {
        var count = 0
        for (item in this) {
            if (!predicate(item))
                break
            count++
        }
        return count
    }

    fun part1(input: List<String>): Int {
        val map = input.map { it.map(Char::digitToInt) }
        var treeCount = 0

        for (y in 1 until map.size - 1) {
            for (x in 1 until map[y].size - 1) {
                val left by lazy { (0 until x).all { map[y][it] < map[y][x] }  }
                val right by lazy { (x + 1 until map[y].size).all { map[y][it] < map[y][x] } }
                val bottom by lazy { (y + 1 until map.size).all { map[it][x] < map[y][x] } }
                val top by lazy { (0 until y).all { map[it][x] < map[y][x] } }

                if (left || right || bottom || top) {
                    treeCount++
                }
            }
        }

        return 2 * (map.size - 2) + 2 * (map[0].size) + treeCount
    }

    fun part2(input: List<String>): Int {
        val map = input.map { it.map(Char::digitToInt) }
        var score = 0

        for (y in 1 until map.size - 1) {
            for (x in 1 until map[y].size - 1) {
                val left = (x - 1 downTo  1).countWhile { map[y][it] < map[y][x] } + 1
                val right = (x + 1 until map[y].size - 1).countWhile { map[y][it] < map[y][x] } + 1
                val bottom = (y + 1 until map.size - 1).countWhile { map[it][x] < map[y][x] } + 1
                val top = (y - 1 downTo  1).countWhile { map[it][x] < map[y][x] } + 1

                score = max(score, left * right * bottom * top)
            }
        }

        return score
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    checkResult(part1(testInput), 21)
    checkResult(part2(testInput), 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
