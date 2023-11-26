package de.havox_design.aoc2022.day21

data class Riddle(private var operandA: String, private var operation: Operator, private var operandB: String) {
    fun calculate(monkeys: Collection<Monkey>): Long {
        val monkeyA = monkeys.first { monkey -> monkey.name == operandA }
        val monkeyB = monkeys.first { monkey -> monkey.name == operandB }

        return when (operation) {
            Operator.PLUS -> monkeyA.calculateValue(monkeys) + monkeyB.calculateValue(monkeys)
            Operator.MINUS -> monkeyA.calculateValue(monkeys) - monkeyB.calculateValue(monkeys)
            Operator.MULTIPLY -> monkeyA.calculateValue(monkeys) * monkeyB.calculateValue(monkeys)
            Operator.DIVIDE -> monkeyA.calculateValue(monkeys) / monkeyB.calculateValue(monkeys)
            Operator.EQUALS -> monkeyA.calculateValue(monkeys).compareTo(monkeyB.calculateValue(monkeys)).toLong()
        }
    }

    @SuppressWarnings("kotlin:S6519")
    fun toCalculation(monkeys: Collection<Monkey>, variablesMonkey: String): String {
        val monkeyA = monkeys.first { monkey -> monkey.name == operandA }
        val monkeyB = monkeys.first { monkey -> monkey.name == operandB }

        var monkeyAString = monkeyA.toCalculation(monkeys, variablesMonkey)
        val operatorString = when (operation) {
            Operator.PLUS -> "+"
            Operator.MINUS -> "-"
            Operator.MULTIPLY -> "*"
            Operator.DIVIDE -> "/"
            Operator.EQUALS -> "="
        }
        var monkeyBString = monkeyB.toCalculation(monkeys, variablesMonkey)
        val leftBraces = if (operatorString.equals(Operator.EQUALS)) "" else "("
        val rightBraces = if (operatorString.equals(Operator.EQUALS)) "" else ")"

        if (!monkeyAString.contains(" x ")) {
            monkeyAString = monkeyA.calculateValue(monkeys).toString()
        }
        if (!monkeyBString.contains(" x ")) {
            monkeyBString = monkeyB.calculateValue(monkeys).toString()
        }

        return "$leftBraces$monkeyAString $operatorString $monkeyBString$rightBraces"
    }
}
