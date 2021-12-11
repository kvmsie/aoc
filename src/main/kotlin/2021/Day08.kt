package `2021`

import readInput

fun main() {

    fun part1(input: List<String>) =
        input.sumOf {
            it.substringAfter(" | ")
                .split(" ")
                .count { signal ->
                    signal.length in arrayOf(2, 3, 4, 7)
                }
        }

    data class Signal(
        val pattern: List<String>,
        val keys: List<String>
    )

    fun part2(input: List<String>) {
        val sum = input.sumOf { entry ->
            val signal = Signal(entry.substringBefore(" | ").split(" "), entry.substringAfter(" | ").split(" "))

            val one = signal.pattern.first { it.length == 2 }
            val four = signal.pattern.first { it.length == 4 }
            val seven = signal.pattern.first { it.length == 3 }
            val eight = signal.pattern.first { it.length == 7 }

            val fiveDigits = signal.pattern.filter { it.length == 5 }

            val three = fiveDigits.first { one.all { char -> char in it } }

            val s1 = seven.filterNot { it in one }
            val s2 = four.filterNot { it in three }
            val s5 = eight.filterNot { it in three + s2 }
            val s7 = eight.filterNot { it in four + s1 + s5 }

            val two = fiveDigits.first {
                it.contains(s5)
            }

            val s3 = two.first { it in one }
            val s4 = three.filterNot { it in seven + s7 }
            val s6 = one.filterNot { it == s3 }

            var output = ""

            signal.keys.forEach {
                when (it.length) {
                    2 -> output += 1
                    3 -> output += 7
                    4 -> output += 4
                    7 -> output += 8
                    5 -> {
                        output +=
                            if (it.all { char -> char in s1 + s3 + s4 + s5 + s7 }) 2
                            else if (it.all { char -> char in s1 + s3 + s4 + s6 + s7 }) 3
                            else 5
                    }
                    6 -> {
                        output +=
                            if (it.all { char -> char in s1 + s2 + s4 + s5 + s6 + s7 }) 6
                            else if (it.all { char -> char in s1 + s2 + s3 + s4 + s6 + s7 }) 9
                            else 0
                    }
                }
            }

            output.toInt()
        }

        println(sum)
    }

    val input = readInput("2021\\Day08")
    part1(input)
    part2(input)
}
