package de.havox_design.aoc2022.day11

class MonkeyInTheMiddle(private var filename: String) {
    val monkeys = readData()

    fun processPart1(rounds: Int): Int {
        for(round in 0 until rounds) {
            for (monkey in monkeys) {
                monkey.inspectItems()
            }
        }

        // Search the quantity of inspections of two most active monkeys
        var mostInspections = Int.MIN_VALUE
        var secondMostInspections = Int.MIN_VALUE
        for(monkey in monkeys) {
            if(monkey.numberOfInspectedItems > mostInspections) {
                secondMostInspections = mostInspections
                mostInspections = monkey.numberOfInspectedItems
            }
            else if(monkey.numberOfInspectedItems > secondMostInspections) {
                secondMostInspections = monkey.numberOfInspectedItems
            }
        }

        return mostInspections * secondMostInspections
    }

    private fun readData(): List<Monkey> {
        val fileData = getResourceAsText(filename)
        val monkeys = emptyList<Monkey>().toMutableList()

        var currentMonkey: Monkey? = null
        var currentStartingItems: List<Item> = emptyList()
        var currentOperation: (Int) -> Int = { a -> a }
        var currentTest: Int? = null
        var currentTrueMonkey: Int? = null
        var currentFalseMonkey: Int? = null
        for (row in fileData) {
            if (row.isBlank()) {
                createMonkey(
                    currentMonkey!!,
                    currentStartingItems,
                    currentOperation,
                    currentTest!!,
                    currentTrueMonkey!!,
                    currentFalseMonkey!!
                )
                monkeys.add(currentMonkey)

                currentMonkey = null
                currentStartingItems = emptyList()
                currentOperation = { a -> a }
                currentTest = null
                currentTrueMonkey = null
                currentFalseMonkey = null
            } else if (row.startsWith("Monkey ")) {
                currentMonkey = processMonkeyRow(row)
            } else if (row.trim().startsWith("Starting items:")) {
                currentStartingItems = processStartingItemsRow(row)
            } else if (row.trim().startsWith("Operation:")) {
                currentOperation = processOperationsRow(row)
            } else if (row.trim().startsWith("Test:")) {
                currentTest = processTestRow(row)
            } else if (row.trim().startsWith("If true:")) {
                currentTrueMonkey = processThrowToMonkeyRow(row)
            } else if (row.trim().startsWith("If false:")) {
                currentFalseMonkey = processThrowToMonkeyRow(row)
            }
        }

        // Add last monkey
        createMonkey(
            currentMonkey!!,
            currentStartingItems,
            currentOperation,
            currentTest!!,
            currentTrueMonkey!!,
            currentFalseMonkey!!
        )
        monkeys += currentMonkey

        return monkeys
    }

    private fun createMonkey(
        currentMonkey: Monkey,
        currentStartingItems: List<Item>,
        currentOperation: (Int) -> Int,
        currentTest: Int,
        currentTrueMonkey: Int,
        currentFalseMonkey: Int
    ) {
        currentMonkey.startingItems = currentStartingItems
        currentMonkey.operation = currentOperation
        currentMonkey.addTestParameter(currentTest, currentTrueMonkey, currentFalseMonkey)
    }

    private fun processMonkeyRow(row: String): Monkey {
        val id = row.substring(7).split(":")
        return Monkey(id[0].toInt())
    }

    private fun processStartingItemsRow(row: String): List<Item> {
        val startingItems = emptyList<Item>().toMutableList()

        val items = row.trim().split(":")[1].split(",")
        for (item in items) {
            startingItems += Item(item.trim().toInt())
        }

        return startingItems
    }

    private fun processOperationsRow(row: String): (Int) -> Int {
        val elements = row.split("new =")[1].trim().split(" ")

        return when (elements[1]) {
            "+" -> {
                { old -> getCorrectValue(elements[0], old) + getCorrectValue(elements[2], old) }
            }

            "-" -> {
                { old -> getCorrectValue(elements[0], old) - getCorrectValue(elements[2], old) }
            }

            "*" -> {
                { old -> getCorrectValue(elements[0], old) * getCorrectValue(elements[2], old) }
            }

            "/" -> {
                { old -> getCorrectValue(elements[0], old) / getCorrectValue(elements[2], old) }
            }

            else -> {
                { old -> old }
            }
        }
    }

    private fun processTestRow(row: String): Int =
        row.split("divisible by")[1].trim().toInt()

    private fun processThrowToMonkeyRow(row: String): Int =
        row.split("throw to monkey")[1].trim().toInt()

    private fun getCorrectValue(value: String, other: Int, paramName: String = "old"): Int {
        val trimmedValue = value.trim()

        return if (trimmedValue == paramName) other else trimmedValue.toInt()
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}