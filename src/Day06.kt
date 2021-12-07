import java.math.BigInteger

fun main() {
    fun simulateLife(fishPerCountdown: Map<Long, BigInteger>, days: Int): Long {
        val school = fishPerCountdown.toMutableMap()
        repeat(days) {
            val temp = (0L until 9L).associateWith { school.getOrDefault(it, BigInteger.ZERO) }
            temp.keys.forEach {
                when (it) {
                    6L -> school[6] = temp[7]!! + temp[0]!!
                    8L -> school[8] = temp[0]!!
                    else -> school[it] = temp[it + 1]!!
                }
            }
        }
        return school.values.sumOf { it.toLong() }
    }

    val testInput = readNumberOfFishPerCountdown("Day06_test")
    check(simulateLife(testInput, 80) == 5934L)
    check(simulateLife(testInput, 256) == 26984457539)

    val input = readNumberOfFishPerCountdown("Day06")
    println(simulateLife(input, 80))
    println(simulateLife(input, 256))
}

private fun readNumberOfFishPerCountdown(file: String) =
    readInput(file).first().split(",").map { it.toLong() }.groupBy { it }
        .mapValues { BigInteger.valueOf(it.value.size.toLong()) }