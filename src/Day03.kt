fun main() {
    fun calculateChars(mutableInput: List<String>, column: Int): MutableMap<Char, Int> {
        val countPerColumn = mutableMapOf<Char, Int>()
        for (line in mutableInput) {
            val char = line[column]
            val charCount = countPerColumn.getOrPut(char) { 1 }
            countPerColumn[char] = charCount + 1
        }
        return countPerColumn
    }

    fun countIsEqual(countPerColumn: MutableMap<Char, Int>) = countPerColumn.values.distinct().size == 1

    fun mostCommonChar(countPerColumn: MutableMap<Char, Int>) =
        if (countIsEqual(countPerColumn)) { '1' } else countPerColumn.maxByOrNull { it.value }!!.key

    fun leastCommonChar(countPerColumn: MutableMap<Char, Int>) =
        if (countIsEqual(countPerColumn)) { '0' } else countPerColumn.minByOrNull { it.value }!!.key

    fun oxygen(input: List<String>, column: Int): String {
        val countPerColumn = calculateChars(input, column)
        val filteredInput = input.filter { line -> line[column] == mostCommonChar(countPerColumn) }
        if (filteredInput.size == 1) {
            return filteredInput.first()
        }
        return oxygen(filteredInput, column + 1)
    }

    fun co2(input: List<String>, column: Int): String {
        val countPerColumn = calculateChars(input, column)
        val filteredInput = input.filter { line -> line[column] == leastCommonChar(countPerColumn) }
        if (filteredInput.size == 1) {
            return filteredInput.first()
        }
        return co2(filteredInput, column + 1)
    }


    fun part2(input: List<String>): Int {
        return oxygen(input, 0).toInt(2) *  co2(input,0).toInt(2);
    }

    fun part1(input: List<String>): Int {
        val elementCountPerColumn = mutableMapOf<Int, MutableMap<Char, Int>>()
        for (line in input) {
            val lineArray = line.toCharArray()
            for ((index, char) in lineArray.withIndex()) {
                val countPerIndex = elementCountPerColumn.getOrPut(index) { mutableMapOf(Pair(char, 1)) }
                val countPerChar = countPerIndex.getOrPut(char) { 1 }
                countPerIndex[char] = countPerChar + 1
            }
        }

        val gamma = elementCountPerColumn
            .map { it.value.maxByOrNull { count -> count.value }!!.key.toString() }
            .joinToString("")

        val epsilon = gamma.replace("0", "x").replace("1", "0").replace("x", "1")

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

