package de.havox_design.aoc2022.day21

data class Riddle(private var operandA: String, private var operation: Operator, private var operandB: String ) {
    fun calculate(monkeys: Collection<Monkey>): Long {
        val monkeyA = monkeys.first{monkey -> monkey.name == operandA }
        val monkeyB = monkeys.first{monkey -> monkey.name == operandB }

        return when(operation) {
            Operator.PLUS -> monkeyA.calculateValue(monkeys) + monkeyB.calculateValue(monkeys)
            Operator.MINUS -> monkeyA.calculateValue(monkeys) - monkeyB.calculateValue(monkeys)
            Operator.MULTIPLY -> monkeyA.calculateValue(monkeys) * monkeyB.calculateValue(monkeys)
            Operator.DIVIDE -> monkeyA.calculateValue(monkeys) / monkeyB.calculateValue(monkeys)
            Operator.EQUALS -> monkeyA.calculateValue(monkeys).compareTo(monkeyB.calculateValue(monkeys)).toLong()
        }
    }
}