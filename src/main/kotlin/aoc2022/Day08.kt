package aoc2022


import readInputAs2dInt

fun main() {

    fun part1(board: Array<Array<Int>>) {
	    var cnt = 0
	    for (row in board.indices) {
			for (col in board[row].indices) {
				val currentTreeHeight = board[row][col]
				if ((row == 0 || row == board.lastIndex || col == 0 || col == board[row].lastIndex) ||
					board[row].sliceArray(0 until col).all { currentTreeHeight > it } ||
					board[row].sliceArray(col + 1 .. board[row].lastIndex).all { currentTreeHeight > it } ||
					board.sliceArray(0 until row).all { currentTreeHeight > it[col] } ||
					board.sliceArray(row + 1 .. board.lastIndex).all { currentTreeHeight > it[col] }
				) {
					cnt++
				}
			}
	    }
	    println(cnt)
    }

    fun part2(board: Array<Array<Int>>) {
	    var maxScore = 0
	    for (row in 1 until board.lastIndex) {
		    for (col in 1 until board[row].lastIndex) {
			    val currentTreeHeight = board[row][col]
			    var leftScore = 0
			    var rightScore = 0
			    var upScore = 0
			    var downScore = 0

				for (height in board[row].sliceArray(0 until col).reversed()) {
					leftScore++
					if (height >= currentTreeHeight) break
				}

			    for (height in board[row].sliceArray(col + 1 .. board[row].lastIndex)) {
				    rightScore++
				    if (height >= currentTreeHeight) break
			    }

			    for (height in board.sliceArray(0 until row).reversed()) {
				    upScore++
				    if (height[col] >= currentTreeHeight) break
			    }

			    for (height in board.sliceArray(row + 1 .. board.lastIndex)) {
				    downScore++
				    if (height[col] >= currentTreeHeight) break
			    }

			    val score = leftScore * rightScore * upScore * downScore
			    if (score > maxScore) {
					maxScore = score
			    }
		    }
	    }
	    println(maxScore)
    }

    val input = readInputAs2dInt("aoc2022/Day08")
	part1(input)
	part2(input)
}