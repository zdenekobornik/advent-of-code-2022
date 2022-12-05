fun main() {


    fun part1(input: List<String>): Int {
        return input.map {
            it.split(',', limit = 2)
                .map {
                    it.split('-', limit = 2).map(String::toInt)
                }
        }.count { (l, r) ->
            (l[0] >= r[0] && l[1] <= r[1]) || (r[0] >= l[0] && r[1] <= l[1])
        }
    }

    fun part2(input: List<String>): Int {
        return input.map {
            it.split(',', limit = 2)
                .map {
                    it.split('-', limit = 2).map(String::toInt)
                }
        }.count { (left, right) ->
            val (l0, l1) = left
            val (r0, r1) = right

            l0 in r0..r1 || l1 in r0..r1 || l0 >= r0 && l1 <= r1 || r0 >= l0 && r1 <= l1
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    checkResult(part1(testInput), 2)
    checkResult(part2(testInput), 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
