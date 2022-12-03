package de.havox_design.aoc2022.day03

class RucksackReorganization(val filename: String) {
    fun getDuplicatesFromList(): List<Item> {
        var duplicates: List<Item> = emptyList()
        var input: List<String>? = getResourceAsText(filename)

        if (!input.isNullOrEmpty()) {
            for (knapsackContent: String in input) {
                duplicates += Knapsack
                    .getKnapsackForString(knapsackContent)
                    .findDuplicateItems()
            }
        }

        return duplicates
    }

    fun getDuplicatesScoreFromList(): Int {
        var score: Int = 0
        var input: List<String>? = getResourceAsText(filename)

        if (!input.isNullOrEmpty()) {
            for (knapsackContent: String in input) {
                score += Knapsack
                    .getKnapsackForString(knapsackContent)
                    .calculateScoreOfDuplicateItems()
            }
        }

        return score
    }

    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}