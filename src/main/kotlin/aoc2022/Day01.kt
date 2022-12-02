package aoc2022


import readInputAsString

fun main() {

    fun part1(input: List<String>) {
        val max = input.maxOf { elf ->
            elf.lines().sumOf { cal ->
                cal.toInt()
            }
        }
        println(max)
    }

    fun part2(input: List<String>) {
        val sum = input.map { elf ->
            elf.lines().sumOf { cal ->
                cal.toInt()
            }
        }.sortedDescending()
        .take(3)
        .sum()

        println(sum)
    }

    val input = readInputAsString("aoc2022/Day01")
        .split("\n\n")
    part1(input)
    part2(input)
}