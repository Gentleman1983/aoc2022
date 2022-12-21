package de.havox_design.aoc2022.day21

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number yelled by monkeys: ${MonkeyMath("input.txt").processPart1()}")
            println("???: ${MonkeyMath("input.txt").processPart2()}")
        }
    }
}
