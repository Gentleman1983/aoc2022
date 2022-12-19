package de.havox_design.aoc2022.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Sum of all quality levels: ${Day("input.txt").processPart1()}")
            println("???: ${Day("input.txt").processPart2()}")
        }
    }
}
