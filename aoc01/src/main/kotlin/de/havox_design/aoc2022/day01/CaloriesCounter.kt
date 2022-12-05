package de.havox_design.aoc2022.day01

class CaloriesCounter(private val filename: String) {
    fun processFile(): String {
        val elves: List<Elf> = readFile()
        val maxCaloriesElf: Elf = findElfWithMaxCalories(elves)
        return maxCaloriesElf.sumCalories().toString()
    }

    fun processFileTopThree(): String {
        val elves = readFile().toMutableList()

        val topElves = emptyList<Elf>().toMutableList()
        for (index in 1..3) {
            val maxCaloriesElf: Elf = findElfWithMaxCalories(elves)
            topElves += maxCaloriesElf
            elves -= maxCaloriesElf
        }

        var sumCalories = 0
        for (index in topElves.indices) {
            sumCalories += topElves[index].sumCalories()
        }

        return sumCalories.toString()
    }

    private fun readFile(): List<Elf> {
        val contentRows = getResourceAsText(filename)
        return convertDataToSetOfElves(contentRows)
    }

    private fun findElfWithMaxCalories(elves: List<Elf>): Elf {
        var maxSumElf: Elf = elves[0]

        elves.forEach { elf ->
            if (elf.sumCalories() > maxSumElf.sumCalories()) {
                maxSumElf = elf
            }
        }

        return maxSumElf
    }

    private fun convertDataToSetOfElves(rows: List<String>?): List<Elf> {
        val elves = emptyList<Elf>().toMutableList()
        var currentItems = emptyList<Item>().toMutableList()

        if (!rows.isNullOrEmpty()) {
            for (index in rows.indices) {
                val row: String = rows[index]
                if (row.isBlank()) {
                    val elf = Elf(currentItems)
                    elves += elf
                    currentItems = emptyList<Item>().toMutableList()
                } else {
                    val item = Item(row.toInt())
                    currentItems += item
                }
            }
        }

        if (currentItems.isNotEmpty()) {
            val elf = Elf(currentItems)
            elves += elf
        }

        return elves
    }

    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}
