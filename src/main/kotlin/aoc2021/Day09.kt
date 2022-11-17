package aoc2021

import readInput

fun main() {

    data class Point (
        val height: Int,
        var basisPart: Boolean = false
    )

    fun part1(input: List<String>) {
        val points = input.map { row ->
            row.toCharArray().map { char ->
                Character.getNumericValue(char)
            }
        }

        fun isLowPoint(xPos: Int, yPos:Int) : Boolean {
            val point = points[yPos][xPos]

            val left  = points[yPos].getOrElse(xPos - 1) { 9 }       > point
            val right = points[yPos].getOrElse(xPos + 1) { 9 }       > point
            val up    = (points.getOrNull(yPos - 1)?.get(xPos) ?: 9) > point
            val down  = (points.getOrNull(yPos + 1)?.get(xPos) ?: 9) > point

            return left && right && up && down
        }

        val sum = points.foldIndexed(0) { rowIndex, result, row ->
            result + row.foldIndexed(0) { colIndex, colResult, col ->
                if (isLowPoint(colIndex, rowIndex)) colResult + col + 1
                else colResult
            }
        }

        println(sum)
    }

    fun part2(input: List<String>) {
        val points = input.map { row ->
            row.toCharArray().map { char ->
                Point(Character.getNumericValue(char))
            }
        }

        fun isLowPoint(xPos: Int, yPos: Int) : Boolean {
            val point = points[yPos][xPos]

            val left  = (points[yPos].getOrNull(xPos - 1)?.height ?: 9)      > point.height
            val right = (points[yPos].getOrNull(xPos + 1)?.height ?: 9)      > point.height
            val up    = (points.getOrNull(yPos - 1)?.get(xPos)?.height ?: 9) > point.height
            val down  = (points.getOrNull(yPos + 1)?.get(xPos)?.height ?: 9) > point.height

            return left && right && up && down
        }

        fun shouldCountNext(currentPoint: Point, nextPoint: Point) =
            !nextPoint.basisPart &&
            nextPoint.height != 9
            && nextPoint.height > currentPoint.height

        fun calculateBasinSize(xPos: Int, yPos: Int, currentSum: Int = 1) : Int {
            var sum = currentSum
            val currentPoint = points[yPos][xPos].apply {
                basisPart = true
            }

            val leftPoint = points[yPos].getOrNull(xPos - 1)
            leftPoint?.let {
                if (shouldCountNext(currentPoint, leftPoint)) {
                    sum = calculateBasinSize(xPos - 1, yPos, sum + 1)
                }
            }

            val upPoint = points.getOrNull(yPos - 1)?.get(xPos)
            upPoint?.let {
                if (shouldCountNext(currentPoint, upPoint)) {
                    sum = calculateBasinSize(xPos, yPos - 1, sum + 1)
                }
            }

            val rightPoint = points[yPos].getOrNull(xPos + 1)
            rightPoint?.let {
                if (shouldCountNext(currentPoint, rightPoint)) {
                    sum = calculateBasinSize(xPos + 1, yPos, sum + 1)
                }
            }

            val downPoint = points.getOrNull(yPos + 1)?.get(xPos)
            downPoint?.let {
                if (shouldCountNext(currentPoint, downPoint)) {
                    sum = calculateBasinSize(xPos, yPos + 1, sum + 1)
                }
            }

            return sum
        }

        val basins = mutableListOf<Int>()

        for (rowIndex in points.indices) {
            for (colIndex in points[rowIndex].indices) {
                if (isLowPoint(colIndex, rowIndex)) {
                    basins.add(calculateBasinSize(colIndex, rowIndex))
                }
            }
        }

        val result = basins.sortedDescending()
            .take(3)
            .fold(1) { result, value ->
                result * value
            }

        println(result)
    }

    val input = readInput("aoc2021/Day09")
    part1(input)
    part2(input)
}

