import kotlin.math.abs
import kotlin.math.sign

fun main() {

    data class Point(var x: Int, var y: Int)

    fun printMap(points: List<Point>) {
        for (y in -5..0) {
            for (x in 0..6) {
                val index = points.indexOf(Point(x, y))
                if (index == 0) {
                    print("H")
                } else if (index != -1) {
                    print(index)
                } else if (x == 0 && y == 0) {
                    print("S")
                } else if (index == -1) {
                    print(".")
                }
            }
            println()
        }
        println()

        println("--------------- ---------- ")
        println()
    }

    fun part1(input: List<String>): Int {
        val commands = input.map {
            val (move, count) = it.split(' ')
            move.first() to count.toInt()
        }

        val head = Point(0, 0)
        val tail = Point(0, 0)

        val visited = mutableSetOf<Point>()

        for ((move, count) in commands) {
            repeat(count) {
                val prevHead = head.copy()

                when (move) {
                    'R' -> head.x++
                    'U' -> head.y--
                    'L' -> head.x--
                    'D' -> head.y++
                }

                val dx = head.x - tail.x
                val dy = head.y - tail.y

                if (abs(dx) > 1 || abs(dy) > 1) {
                    tail.x = prevHead.x
                    tail.y = prevHead.y
                }

                visited.add(tail.copy())
            }
        }

        return visited.count()
    }



    fun part2(input: List<String>): Int {
        val commands = input.map {
            val (move, count) = it.split(' ')
            move.first() to count.toInt()
        }

        val points = (1..10).map { Point(0, 0) }
        val head = points[0]
        val visited = mutableSetOf<Point>()

        for ((move, count) in commands) {
            repeat(count) {
                when (move) {
                    'R' -> head.x++
                    'U' -> head.y--
                    'L' -> head.x--
                    'D' -> head.y++
                }

                for (index in 1 until points.size) {
                    val prevPoint = points[index - 1]
                    val point = points[index]

                    val dx = prevPoint.x - point.x
                    val dy = prevPoint.y - point.y

                    if (abs(dx) > 1 || abs(dy) > 1) {
                        point.x = point.x + dx.sign
                        point.y = point.y + dy.sign
                    }
                }

                visited.add(points.last().copy())
            }
        }

        return visited.count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    checkResult(part1(testInput), 13)
    val testInput2 = readInput("Day09_test2")
    checkResult(part2(testInput2), 36)
    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
