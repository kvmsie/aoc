package aoc2018

import readInput

fun main() {
    fun part1(input: List<Int>) {
        input.forEachIndexed { index, i ->
            for (j in input.drop(index + 1)) {
                if (i + j == 2020) {
                    println(i*j)
                    return
                }
            }
        }
    }

    fun part2(input: List<Int>) {

    }

    val input = readInput("test")
//    part1(input)
//    part2(input)
}