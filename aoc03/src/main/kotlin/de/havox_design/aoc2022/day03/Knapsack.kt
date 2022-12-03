package de.havox_design.aoc2022.day03

class Knapsack(val leftCompartment: List<Item>, val rightCompartment: List<Item>) {
    fun findDuplicateItems(): Set<Item> {
        var duplicateItems: Set<Item> = emptySet()

        for(lefItem: Item in leftCompartment) {
            for(rightItem: Item in rightCompartment) {
                if(lefItem == rightItem) {
                    duplicateItems += lefItem
                }
            }
        }

        return  duplicateItems
    }

    fun calculateScoreOfDuplicateItems(): Int {
        var duplicateItems: Set<Item> = findDuplicateItems()
        var duplicateScore: Int = 0

        for(item: Item in duplicateItems) {
            duplicateScore += item.getScoreForItem()
        }

        return duplicateScore
    }

    companion object {
        fun getKnapsackForString(s: String): Knapsack {
            var leftCompartment: List<Item> = emptyList()
            var rightCompartment: List<Item> = emptyList()
            var leftString: String = s.substring(0, s.length / 2)
            var rightString: String = s.substring(s.length / 2, s.length)

            for(index in leftString.indices) {
                leftCompartment += Item(leftString.substring(index, index + 1))
            }

            for(index in rightString.indices) {
                rightCompartment += Item(rightString.substring(index, index + 1))
            }

            return Knapsack(leftCompartment, rightCompartment)
        }
    }
}