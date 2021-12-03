fun main() {
    fun countIncreases(counts: List<Int>) =
        (1 until counts.size).count { counts[it] > counts[it - 1] }

    fun part1(input: List<String>): Int {
        val intInput = input.map { it.toInt() }
        return countIncreases(intInput)
    }

    fun part2(input: List<String>): Int {
        val intInput = input.map { it.toInt() }
        val windowed = intInput.windowed(3, 1).map { it.sum() }
        return countIncreases(windowed)
    }

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
