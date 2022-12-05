package de.havox_design.aoc2022.day05

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Top elements of all Cratemaster 9000 stacks are: '${SupplyStacks("input.txt").evaluateTask1()}'")
            println("Top elements of all Cratemaster 9001 stacks are: '${SupplyStacks("input.txt").evaluateTask2()}'")
        }
    }
}
