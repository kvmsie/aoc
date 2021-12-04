fun main() {
    fun part1(input: List<Int>) {
        val count = input.zipWithNext()
            .count { it.first < it.second }

        println(count)
    }

    fun part2(input: List<Int>) {
        val count = input.windowed(3)
            .map { it.sum() }
            .zipWithNext()
            .count { it.first < it.second }

        println(count)
    }

    val input = readInputAsInt("Day01")
    part1(input)
    part2(input)
}