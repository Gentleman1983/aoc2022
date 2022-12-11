package de.havox_design.aoc2022.day11

class Monkey(private val id: Int, var startingItems: List<Item> = emptyList<Item>().toMutableList()) {
    init {
        allMonkeys[id] = this
    }

    var divisibleBy: Int = 1
    var falseThrowToMonkey: Int = id
    var trueThrowToMonkey: Int = id
    var operation: (Int) -> Int = { worry -> worry + 1 }
    val getBored: (Int) -> Int = { worry -> worry / 3}

    fun addTestParameter(divisibleBy: Int, trueThrowToMonkey: Int, falseThrowToMonkey: Int) {
        this.divisibleBy = divisibleBy
        this.trueThrowToMonkey = trueThrowToMonkey
        this.falseThrowToMonkey = falseThrowToMonkey
    }

    fun inspectItems() {
        for(item in startingItems) {
            inspectItem(item)
        }
    }

    private fun inspectItem(item: Item) {
        item.worryLevel = operation(item.worryLevel)
        item.worryLevel = getBored(item.worryLevel)

        var nextMonkey = if(item.worryLevel % divisibleBy == 0) {
            getMonkeyForId(trueThrowToMonkey)
        }
        else {
            getMonkeyForId(falseThrowToMonkey)
        }
        nextMonkey!!.startingItems += item
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
        var result = id
        result = 31 * result + startingItems.hashCode()
        result = 31 * result + divisibleBy
        result = 31 * result + falseThrowToMonkey
        result = 31 * result + trueThrowToMonkey
        return result
    }

    companion object {
        private val allMonkeys = emptyMap<Int, Monkey>().toMutableMap()

        fun getMonkeyForId(id: Int): Monkey? =
            allMonkeys[id]
    }
}
