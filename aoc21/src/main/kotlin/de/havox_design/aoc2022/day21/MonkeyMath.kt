package de.havox_design.aoc2022.day21

import java.lang.IllegalArgumentException

class MonkeyMath(private var filename: String) {
    val data = readFile()

    fun processPart1(): Long =
        data
            .first { monkey -> monkey.name == "root" }
            .calculateValue(data)

    fun processPart2(): Long =
        0

    private fun readFile() =
        getResourceAsText(filename).map(::processRow)

    private fun processRow(row: String): Monkey {
        val monkeyName = row
            .substringBefore(": ")
        val monkeyValue = row
            .substringAfter(": ")
        var monkeyRiddle: Riddle? = null
        var monkeyNumber: Long? = null
        if (monkeyValue.contains(" ")) {
            val monkeyValues = monkeyValue
                .split(" ")
            val firstOperand = monkeyValues[0]
            val operator = when (monkeyValues[1]) {
                "+" -> Operator.PLUS
                "-" -> Operator.MINUS
                "*" -> Operator.MULTIPLY
                "/" -> Operator.DIVIDE
                else -> throw IllegalArgumentException("Unknown operand '${monkeyValues[1]}'")
            }
            val secondOperand = monkeyValues[2]
            monkeyRiddle = Riddle(firstOperand, operator, secondOperand)
        } else {
            monkeyNumber = monkeyValue
                .toLong()
        }

        return Monkey(monkeyName, monkeyRiddle, monkeyNumber)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
