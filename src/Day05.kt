
fun main() {
    fun getStacksFromSupplies(suppliesString: String): Array<ArrayDeque<Char>> {
        val supplies = suppliesString.lines()
            .map { it.chunked(4).map { it.filterNot { it == '[' || it == ']' }.trim() } }.dropLast(1)
        val columns = supplies.first().size

        val stacks = Array<ArrayDeque<Char>>(columns) { ArrayDeque() }

        for (row in supplies) {
            for (column in row.indices) {
                if (row[column].isBlank()) {
                    continue
                }

                stacks[column].add(row[column].first())
            }
        }

        return stacks
    }

    fun part1(input: String): String {
        val (suppliesRaw, movesRaw) = input.split("\n\n")

        val moves = movesRaw.lines()
        val stacks = getStacksFromSupplies(suppliesRaw)

        for (move in moves) {
            val (num, srcStack, destStack) = move.split(' ').map { it.trim().toIntOrNull() }.filterNotNull()

            repeat(num) {
                stacks[destStack - 1].addFirst(stacks[srcStack - 1].removeFirst())
            }
        }

        return stacks.map { it.first() }.joinToString(separator = "")
    }

    fun part2(input: String): String {
        val (suppliesRaw, movesRaw) = input.split("\n\n")

        val moves = movesRaw.lines()
        val stacks = getStacksFromSupplies(suppliesRaw)

        for (move in moves) {
            val (num, srcStack, destStack) = move.split(' ').map { it.trim().toIntOrNull() }.filterNotNull()

            for (step in 0 until num) {
                stacks[destStack - 1].add(step, stacks[srcStack - 1].removeFirst())
            }
        }

        return stacks.map { it.first() }.joinToString(separator = "")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputRaw("Day05_test")
    checkResult(part1(testInput), "CMZ")
    checkResult(part2(testInput), "MCD")

    val input = readInputRaw("Day05")
    println(part1(input))
    println(part2(input))
}
