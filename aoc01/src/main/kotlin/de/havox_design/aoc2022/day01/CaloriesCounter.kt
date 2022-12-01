package de.havox_design.aoc2022.day01

class CaloriesCounter(val filename: String) {
    fun processFile(): String {
        var elves: List<Elf> = readFile()
        var maxCaloriesElf: Elf = findElfWithMaxCalories(elves)
        return maxCaloriesElf.sumCalories().toString()
    }

    fun processFileTopThree(): String {
        var elves: List<Elf> = readFile()

        var topElves: List<Elf> = emptyList()
        for(index in 1..3) {
            var maxCaloriesElf: Elf = findElfWithMaxCalories(elves)
            topElves += maxCaloriesElf
            elves -= maxCaloriesElf
        }

        var sumCalories: Int = 0
        for(index in topElves.indices) {
            sumCalories += topElves[index].sumCalories()
        }

        return sumCalories.toString()
    }

    private fun readFile(): List<Elf>
            {
                val contentRows = getResourceAsText(filename)
                return convertDataToSetOfElves(contentRows)
            }

    private fun findElfWithMaxCalories(elves: List<Elf>): Elf {
        var maxSumElf: Elf = elves[0]

        elves.forEach { elf ->
            if(elf.sumCalories() > maxSumElf.sumCalories()) {
                maxSumElf = elf
            }
        }

        return maxSumElf
    }

    private fun convertDataToSetOfElves(rows:List<String>?):List<Elf>
            {
                var elves :List<Elf> = emptyList()
                var currentItems: List<Item> = emptyList()

                if(!rows.isNullOrEmpty()) {
                    for (index in rows.indices) {
                        var row: String = rows.get(index)
                        if (row.isNullOrBlank()) {
                            var elf: Elf = Elf(currentItems)
                            elves += elf
                            currentItems = emptyList()
                        } else {
                            var item: Item = Item(row.toInt())
                            currentItems += item
                        }
                    }
                }

                if(currentItems.isNotEmpty()) {
                    var elf: Elf = Elf(currentItems)
                    elves += elf
                }

                return elves
            }

    private fun getResourceAsText(path: String): List<String>? =
            this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}