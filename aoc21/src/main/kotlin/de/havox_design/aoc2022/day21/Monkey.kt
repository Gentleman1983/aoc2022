package de.havox_design.aoc2022.day21

data class Monkey(var name: String, private var riddle: Riddle? = null, var number: Long? = null) {
    fun needsToSolveRiddle(): Boolean =
        number == null

    fun calculateValue(monkeys: Collection<Monkey>): Long =
        if (needsToSolveRiddle()) {
            riddle!!.calculate(monkeys)
        } else {
            number!!
        }

    fun toCalculation(monkeys: Collection<Monkey>, variablesMonkey: String): String =
        if (variablesMonkey.contains(name)) {
            " x "
        } else if (needsToSolveRiddle()) {
            riddle!!.toCalculation(monkeys, variablesMonkey)
        } else {
            number.toString()
        }
}
