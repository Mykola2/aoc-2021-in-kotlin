import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        return input
            .map { it.split(" -> ") }
            .map { it.map { it.split(",").map { it.toInt() } }.map { Pair(it[0], it[1]) } }
            .filter { it[0].first == it[1].first || it[0].second == it[1].second }
            .flatMap {
                it[0].first.toward(it[1].first)
                    .map { x -> it[0].second.toward(it[1].second).map { Pair(x, it) } }.flatten()
            }
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }.count()


    }

    fun part2(input: List<String>): Int {
        return input
            .map { it.split(" -> ") }
            .map { it.map { it.split(",").map { it.toInt() } }.map { Pair(it[0], it[1]) } }
            .filter {
                it[0].first == it[1].first || it[0].second == it[1].second || abs(it[0].first - it[1].first) == abs(
                    it[0].second - it[1].second
                )
            }
            .flatMap {
                if (abs(it[0].first - it[1].first) == abs(it[0].second - it[1].second)) {
                    it[0].first.toward(it[1].first).zip(it[0].second.toward(it[1].second))
                } else {
                    it[0].first.toward(it[1].first)
                        .map { x -> it[0].second.toward(it[1].second).map { Pair(x, it) } }.flatten()
                }
            }
            .groupingBy { it }
            .eachCount()
            .filter { it.value > 1 }
            .count()

    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}

private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}