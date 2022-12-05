package de.havox_design.aoc2022.day03

class RucksackReorganization(private val filename: String) {
    fun getDuplicatesFromList(): List<Item> {
        val duplicates = emptyList<Item>().toMutableList()
        val input: List<String>? = getResourceAsText(filename)

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
        var score = 0
        val input: List<String>? = getResourceAsText(filename)

        if (!input.isNullOrEmpty()) {
            for (knapsackContent: String in input) {
                score += Knapsack
                    .getKnapsackForString(knapsackContent)
                    .calculateScoreOfDuplicateItems()
            }
        }

        return score
    }

    fun detectBadgesFromList(): List<Item> {
        val badges = emptyList<Item>().toMutableList()
        val input: List<String>? = getResourceAsText(filename)

        if (!input.isNullOrEmpty()) {
            for (index in 0..(input.size - 1) / 3) {
                val idxTeam1: Int = index * 3
                val itemsTeam1: Set<Item> = Knapsack
                    .getKnapsackForString(input[idxTeam1])
                    .listDistinctItems()
                val idxTeam2: Int = index * 3 + 1
                val itemsTeam2: Set<Item> = Knapsack
                    .getKnapsackForString(input[idxTeam2])
                    .listDistinctItems()
                val idxTeam3: Int = index * 3 + 2
                val itemsTeam3: Set<Item> = Knapsack
                    .getKnapsackForString(input[idxTeam3])
                    .listDistinctItems()

                badges += findElementsContainedInAllKnapsacks(itemsTeam1, itemsTeam2, itemsTeam3)
            }
        }

        return badges
    }

    fun getBadgesScoreFromList(): Int {
        var score = 0

        for (item in detectBadgesFromList()) {
            score += item.getScoreForItem()
        }

        return score
    }

    private fun findElementsContainedInAllKnapsacks(ks1: Set<Item>, ks2: Set<Item>, ks3: Set<Item>): List<Item> {
        val badges = emptyList<Item>().toMutableList()

        for (currentItem in ks1) {
            for (itemKs2 in ks2) {
                if (currentItem == itemKs2) {
                    for (itemKs3 in ks3) {
                        if (currentItem == itemKs3) {
                            badges += currentItem
                        }
                    }
                }
            }
        }

        return badges
    }

    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}
