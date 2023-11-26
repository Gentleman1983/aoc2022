package de.havox_design.aoc2022.day11

import java.math.BigInteger

class Monkey(
    private val id: Int,
    var startingItems: List<Item> = emptyList<Item>().toMutableList(),
    var modificationOfWorryLevelIfBoredActive: Boolean = true
) {
    init {
        allMonkeys[id] = this
    }

    var numberOfInspectedItems: BigInteger = BigInteger.ZERO
    var divisibleBy: BigInteger = BigInteger.ONE
    var falseThrowToMonkey: Int = id
    var trueThrowToMonkey: Int = id
    var operation: (BigInteger ) -> BigInteger  = { worry -> worry + BigInteger.ONE }
    var getBored: (BigInteger ) -> BigInteger  = { worry -> worry / BigInteger.valueOf(3) }

    fun addTestParameter(divisibleBy: BigInteger, trueThrowToMonkey: Int, falseThrowToMonkey: Int) {
        this.divisibleBy = divisibleBy
        this.trueThrowToMonkey = trueThrowToMonkey
        this.falseThrowToMonkey = falseThrowToMonkey
    }

    fun inspectItems() {
        for (item in startingItems) {
            inspectItem(item)
        }
    }

    private fun inspectItem(item: Item) {
        item.worryLevel = operation(item.worryLevel)
        numberOfInspectedItems++

        if (modificationOfWorryLevelIfBoredActive) {
            item.worryLevel = getBored(item.worryLevel)
        }

        val nextMonkey = if (item.worryLevel % divisibleBy == BigInteger.ZERO) {
            getMonkeyForId(trueThrowToMonkey)
        } else {
            getMonkeyForId(falseThrowToMonkey)
        }

        nextMonkey.startingItems += item
        startingItems -= item
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Monkey

        if (id != other.id) return false
        if (startingItems != other.startingItems) return false
        if (divisibleBy != other.divisibleBy) return false
        if (falseThrowToMonkey != other.falseThrowToMonkey) return false
        if (trueThrowToMonkey != other.trueThrowToMonkey) return false

        return true
    }

    override fun hashCode(): Int {
        var result: Long = id.toLong()
        result = 31 * result + startingItems.hashCode()
        result = 31 * result + divisibleBy.toLong()
        result = 31 * result + falseThrowToMonkey
        result = 31 * result + trueThrowToMonkey
        return (result % Int.MAX_VALUE.toLong()).toInt()
    }

    companion object {
        private val allMonkeys = emptyMap<Int, Monkey>().toMutableMap()

        @SuppressWarnings("kotlin:S6611")
        fun getMonkeyForId(id: Int): Monkey =
            allMonkeys[id]!!
    }
}
