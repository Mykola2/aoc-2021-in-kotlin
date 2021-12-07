import kotlin.math.abs

fun main() {

    fun part1(nums: List<Int>): Int {
        return (0 until nums.maxOf { it })
            .map { i -> nums.sumOf { abs(it - i) } }
            .minOf { it }
    }

    fun part2(nums: List<Int>): Int {
        return (0 until nums.maxOf { it })
            .map { i ->
                nums.sumOf {
                    val diff = abs(it - i)
                    diff * (diff + 1) / 2
                }
            }
            .minOf { it }

    }

    val testInput = readNumbers("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readNumbers("Day07")
    println(part1(input))
    println(part2(input))
}

private fun readNumbers(file: String) = readInput(file).first().split(",").map { it.toInt() }
