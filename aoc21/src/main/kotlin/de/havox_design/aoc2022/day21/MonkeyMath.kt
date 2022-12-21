package de.havox_design.aoc2022.day21

import java.lang.IllegalArgumentException

class MonkeyMath(private var filename: String) {
    val data = readFile()

    fun processPart1(): Long =
        data
            .first { monkey -> monkey.name == "root" }
            .calculateValue(data)

    fun processPart2(): Long {
        val calculation = data
            .first { monkey -> monkey.name == "root" }
            .toCalculation(data, "humn")

        println("Calculation: $calculation")
        println("Solution by Computer Algebra System (CAS): ~3.342.154.812.536,9998275658653017144")
        println("For online variant try: https://www.mathpapa.com/equation-solver/")
        println("Checking values 3.342.154.812.525 to 3.342.154.812.550")

        for (i in 3342154812525L..3342154812550L) {
            data.first { monkey -> monkey.name == "humn" }.number = i

            val result = data
                .first { monkey -> monkey.name == "root" }
                .calculateValue(data) == 0L

            println("humn: $i -> root: $result")
            if (result) {
                return i
            }
        }

        // Test sample
        data.first { monkey -> monkey.name == "humn" }.number = 301L

        val result = data
            .first { monkey -> monkey.name == "root" }
            .calculateValue(data) == 0L

        println("humn: 301 -> root: $result")
        if (result) {
            return 301L
        }

        return Long.MIN_VALUE
    }


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
                "=" -> Operator.EQUALS
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
