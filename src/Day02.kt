fun main() {
    fun part1(input: List<String>): Int {
        var hor = 0
        var depth = 0
        input.forEach { move ->
            val movePair = move.split(" ")
            val direction = movePair[0]
            val value = movePair[1].toInt()
            when (direction) {
                "forward" -> hor += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }
        return hor * depth
    }

    fun part2(input: List<String>): Int {
        var hor = 0
        var depth = 0
        var aim = 0
        input.forEach { move ->
            val movePair = move.split(" ")
            val direction = movePair[0]
            val value = movePair[1].toInt()
            when (direction) {
                "forward" -> {
                    hor += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        return hor * depth
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
