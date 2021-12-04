fun main() {
    fun part1(input: List<String>) {
        val directions = input.map { it.split(" ") }

        var depth = 0
        var horizontal = 0

        for ((instruction, valueStr) in directions) {
            val value = valueStr.toInt()
            when (instruction) {
                "forward" -> horizontal += value
                "up" -> depth -= value
                "down" -> depth += value
            }
        }

        println("depth: $depth, horizontal: $horizontal, multiply: ${depth * horizontal}")
    }

    fun part2(input: List<String>) {
        val directions = input.map { it.split(" ") }

        var depth = 0
        var horizontal = 0
        var aim = 0

        for ((instruction, valueStr) in directions) {
            val value = valueStr.toInt()
            when (instruction) {
                "forward" -> {
                    horizontal += value
                    depth += (aim * value)
                }
                "up" -> aim -= value
                "down" -> aim += value
            }
        }

        println("depth: $depth, horizontal: $horizontal, multiply: ${depth * horizontal}")
    }

    val input = readInput("Day02")
    part1(input)
    part2(input)
}