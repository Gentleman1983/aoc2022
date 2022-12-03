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

    fun detectBadgesFromList(): List<Item> {
        var badges: List<Item> = emptyList()
        var input: List<String>? = getResourceAsText(filename)

        if (!input.isNullOrEmpty()) {
            for (index in 0..(input.size - 1) / 3) {
                var idxTeam1: Int = index * 3
                var itemsTeam1: Set<Item> = Knapsack
                    .getKnapsackForString(input[idxTeam1])
                    .listDistinctItems()
                var idxTeam2: Int = index * 3 + 1
                var itemsTeam2: Set<Item> = Knapsack
                    .getKnapsackForString(input[idxTeam2])
                    .listDistinctItems()
                var idxTeam3: Int = index * 3 + 2
                var itemsTeam3: Set<Item> = Knapsack
                    .getKnapsackForString(input[idxTeam3])
                    .listDistinctItems()

                badges += findElementsContainedInAllKnapsacks(itemsTeam1, itemsTeam2, itemsTeam3)
            }
        }

        return badges
    }

    fun getBadgesScoreFromList(): Int {
        var score: Int = 0

        for (item in detectBadgesFromList()) {
            score += item.getScoreForItem()
        }

        return score
    }

    private fun findElementsContainedInAllKnapsacks(ks1: Set<Item>, ks2: Set<Item>, ks3: Set<Item>): List<Item> {
        var badges: List<Item> = emptyList()

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