package aoc2022


import readInputAsStringLists

fun main() {

	data class Monkey(
		val startingItems: MutableList<Long>,
		val operation: (Long) -> Long,
		val test: (Long) -> Int,
		val divider: Int,
		var inspectCounter: Long = 0,
	)

    fun part1(input: List<List<String>>) {
		val monkeys = input.associate { monkey ->
			val monkeyId = monkey[0].substringAfter("Monkey ").first().digitToInt()
			val startingItems = monkey[1].substringAfter("Starting items: ").split(", ").map { it.toLong() }.toMutableList()
			val divider = monkey[3].substringAfter("Test: divisible by ").toInt()
			val operation: (Long) -> Long = { level ->
				val instruction = monkey[2].substringAfter("Operation: new = old ").split(" ")
				val operator = instruction[0]
				val num = if (instruction[1] == "old") level else instruction[1].toLong()

				when (operator) {
					"*"  -> level * num
					"+"  -> level + num
					else -> 0
				}
			}
			val test: (Long) -> Int = { level ->
				if (level % divider == 0L) monkey[4].substringAfter("If true: throw to monkey ").toInt()
				else monkey[5].substringAfter("If false: throw to monkey ").toInt()
			}

			monkeyId to Monkey(startingItems, operation, test, divider)
		}

	    repeat(20) {
		    monkeys.values.forEach { monkey ->
			    monkey.startingItems.forEach { level ->
				    val newLevel = monkey.operation(level) / 3
				    val newOwner = monkey.test(newLevel)
				    monkeys[newOwner]!!.startingItems.add(newLevel)
			    }
			    monkey.inspectCounter += monkey.startingItems.size
			    monkey.startingItems.clear()
		    }
	    }

	    val topMonkeys = monkeys.values.sortedByDescending { it.inspectCounter }
	    println(topMonkeys[0].inspectCounter * topMonkeys[1].inspectCounter)
    }

    fun part2(input: List<List<String>>) {
	    val monkeys = input.associate { monkey ->
		    val monkeyId = monkey[0].substringAfter("Monkey ").first().digitToInt()
		    val startingItems = monkey[1].substringAfter("Starting items: ").split(", ").map { it.toLong() }.toMutableList()
		    val divider = monkey[3].substringAfter("Test: divisible by ").toInt()
		    val operation: (Long) -> Long = { level ->
			    val instruction = monkey[2].substringAfter("Operation: new = old ").split(" ")
			    val operator = instruction[0]
			    val num = if (instruction[1] == "old") level else instruction[1].toLong()

			    when (operator) {
				    "*"  -> level * num
				    "+"  -> level + num
				    else -> 0
			    }
		    }
		    val test: (Long) -> Int = { level ->
			    if (level % divider == 0L) monkey[4].substringAfter("If true: throw to monkey ").toInt()
			    else monkey[5].substringAfter("If false: throw to monkey ").toInt()
		    }

		    monkeyId to Monkey(startingItems, operation, test, divider)
	    }

	    val product = monkeys.values.fold(1) { acc, monkey ->
		    acc * monkey.divider
		}

	    repeat(10000) {
		    monkeys.values.forEach { monkey ->
			    monkey.startingItems.forEach { level ->
				    val newLevel = monkey.operation(level) % product
				    val newOwner = monkey.test(newLevel)
				    monkeys[newOwner]!!.startingItems.add(newLevel)
			    }
			    monkey.inspectCounter += monkey.startingItems.size
			    monkey.startingItems.clear()
		    }
	    }

	    val topMonkeys = monkeys.values.sortedByDescending { it.inspectCounter }
	    println(topMonkeys[0].inspectCounter * topMonkeys[1].inspectCounter)
    }

    val input = readInputAsStringLists("aoc2022/Day11")
	part1(input)
	part2(input)
}