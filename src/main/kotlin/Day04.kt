fun main() {

    fun isRowCompleted(board: List<List<Pair<Int, Boolean>>>) =
        board.any { row ->
            row.all { it.second }
        }

    fun isColumnCompleted(board: List<List<Pair<Int, Boolean>>>) : Boolean {
        for (i in board.first().indices) {
            if (board.all { it[i].second }) {
                return true
            }
        }

        return false
    }

    fun part1(input: List<String>) {
        var winningBoardIndex = 0
        var winningNumber = 0

        val numbers = input.first()
            .split(",")
            .map { it.toInt() }

        var boards = input.drop(2)
            .filterNot { it.isEmpty() }
            .map { row ->
                row.split(" ")
                    .filterNot { it.isEmpty() }
                    .map { Pair(it.toInt(), false) }
            }.chunked(5)

        run numberLoop@ {
            numbers.forEach { number ->
                boards = boards.map { board ->
                    board.map { row ->
                        row.map { element ->
                            if (element.first == number) {
                                element.copy(second = true)
                            } else {
                                element
                            }
                        }
                    }
                }

                boards.forEachIndexed { index, board ->
                    if (isRowCompleted(board) || isColumnCompleted(board)) {
                        winningBoardIndex = index
                        winningNumber = number
                        return@numberLoop
                    }
                }
            }
        }

        val unmarkedSum = boards[winningBoardIndex].flatten()
            .filter { !it.second }
            .sumOf { it.first }

        println(winningNumber * unmarkedSum)
    }

    fun part2(input: List<String>) {
        var winningNumber = 0

        val numbers = input.first()
            .split(",")
            .map { it.toInt() }

        var boards = input.drop(2)
            .filterNot { it.isEmpty() }
            .map { row ->
                row.split(" ")
                    .filterNot { it.isEmpty() }
                    .map { Pair(it.toInt(), false) }
            }.chunked(5)

        run numberLoop@ {
            numbers.forEach { number ->
                boards = boards.map { board ->
                    board.map { row ->
                        row.map { element ->
                            if (element.first == number) {
                                element.copy(second = true)
                            } else {
                                element
                            }
                        }
                    }
                }

                if (boards.size != 1) {
                    boards = boards.filterNot { board ->
                        isRowCompleted(board) || isColumnCompleted(board)
                    }

                } else {
                    val board = boards.first()
                    if (isRowCompleted(board) || isColumnCompleted(board)) {
                        winningNumber = number
                        return@numberLoop
                    }
                }
            }
        }

        val unmarkedSum = boards.first().flatten()
            .filter { !it.second }
            .sumOf { it.first }

        println(winningNumber * unmarkedSum)
    }



    val input = readInput("Day04")
    part1(input)
    part2(input)
}