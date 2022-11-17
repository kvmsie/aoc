package aoc2021

import readInts

fun main() {

    fun part(input: List<Int>, days: Int) {
        var fishes = Array(9) { 0L }.toMutableList()

        input.forEach {
            fishes[it] = fishes[it] + 1
        }

        repeat(days) {
            val newFishes = Array(9) { 0L }.toMutableList()

            fishes.forEachIndexed { index, value ->
                if (index != 0) {
                    newFishes[index - 1] = newFishes[index - 1] + value
                }
            }

            newFishes[6] = newFishes[6] + fishes[0]
            newFishes[8] = fishes[0]

            fishes = newFishes
        }

        val sum = fishes.sum()
        println(sum)
    }

    val input = readInts("aoc2021/Day06")
    part(input, 80)
    part(input, 256)

}