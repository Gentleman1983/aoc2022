package de.havox_design.aoc2022.day03

class Knapsack(private val leftCompartment: List<Item>, private val rightCompartment: List<Item>) {
    fun findDuplicateItems(): Set<Item> {
        var duplicateItems: Set<Item> = emptySet()

        for (leftItem: Item in leftCompartment) {
            for (rightItem: Item in rightCompartment) {
                if (leftItem == rightItem) {
                    duplicateItems += leftItem
                }
            }
        }

        return duplicateItems
    }

    fun calculateScoreOfDuplicateItems(): Int {
        val duplicateItems: Set<Item> = findDuplicateItems()
        var duplicateScore = 0

        for (item: Item in duplicateItems) {
            duplicateScore += item.getScoreForItem()
        }

        return duplicateScore
    }

    fun listDistinctItems(): Set<Item> {
        var items: Set<Item> = emptySet()

        for (item: Item in leftCompartment) {
            items += item
        }
        for (item: Item in rightCompartment) {
            items += item
        }

        return items
    }

    companion object {
        fun getKnapsackForString(s: String): Knapsack {
            var leftCompartment: List<Item> = emptyList()
            var rightCompartment: List<Item> = emptyList()
            val leftString: String = s.substring(0, s.length / 2)
            val rightString: String = s.substring(s.length / 2, s.length)

            for (index in leftString.indices) {
                leftCompartment += Item(leftString.substring(index, index + 1))
            }

            for (index in rightString.indices) {
                rightCompartment += Item(rightString.substring(index, index + 1))
            }

            return Knapsack(leftCompartment, rightCompartment)
        }
    }
}