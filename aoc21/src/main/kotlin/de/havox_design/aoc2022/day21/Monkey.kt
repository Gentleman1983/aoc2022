package de.havox_design.aoc2022.day21

data class Monkey(var name: String, private var riddle: Riddle? = null, private var number: Long? = null) {
    fun needsToSolveRiddle(): Boolean =
        number == null

    fun calculateValue(monkeys: Collection<Monkey>): Long =
        if (needsToSolveRiddle()) {
            riddle!!.calculate(monkeys)
        } else {
            number!!
        }
}
