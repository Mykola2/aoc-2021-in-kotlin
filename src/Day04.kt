fun main() {
    fun hitBoard(number: Int, board: List<MutableList<Int>>) {
        board.forEachIndexed { index, ints -> ints.forEachIndexed { i, n -> if (n == number) board[index][i] = 0 } }

    }

    fun checkBoard(board: List<MutableList<Int>>): Int {
        board.forEach { if (it.all { num -> num == 0 }) return board.sumOf { it.sum() } }
        List(board.first().size) { index ->
            if (board.map { it[index] }.all { num -> num == 0 }) return board.sumOf { it.sum() }
        }
        return 0
    }

    fun populateBoards(input: List<String>): MutableList<List<MutableList<Int>>> {
        val boards = input.drop(2)
            .filter { it.isNotEmpty() }
            .map { it.split(" ", "  ").filter { it.isNotEmpty() }.map { it.toInt() } }.map { it.toMutableList() }
            .chunked(5)
            .toMutableList()
        return boards
    }

    fun part1(input: List<String>): Int {
        val boards = populateBoards(input)
        input.first().split(",")
            .forEach { number ->
                boards.forEach { board ->
                    hitBoard(number.toInt(), board)
                    val checkBoard = checkBoard(board)
                    if (checkBoard != 0) return checkBoard * number.toInt()
                }
            }

        return 0
    }

    fun part2(input: List<String>): Int {
        val boards = populateBoards(input)
        input.first().split(",")
            .forEach { number ->
                val iterator = boards.iterator()
                while (iterator.hasNext()){
                    val board = iterator.next()
                    hitBoard(number.toInt(), board)
                    val checkBoard = checkBoard(board)
                    if (checkBoard != 0 && boards.size == 1) return checkBoard * number.toInt()
                    if (checkBoard != 0) iterator.remove()

                }
            }

        return 1
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
