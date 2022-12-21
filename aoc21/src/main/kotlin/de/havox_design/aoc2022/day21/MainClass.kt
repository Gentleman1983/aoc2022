package de.havox_design.aoc2022.day21

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number yelled by monkeys: ${MonkeyMath("inputPart1.txt").processPart1()}")
            println("Which number to yell for equality at root: ${MonkeyMath("inputPart2.txt").processPart2()}")
        }
    }
}
