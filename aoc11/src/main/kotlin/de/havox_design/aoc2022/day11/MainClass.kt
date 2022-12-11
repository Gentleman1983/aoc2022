package de.havox_design.aoc2022.day11

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Monkey Business: ${MonkeyInTheMiddle("input.txt").processPart1(20)}")
            println("Monkey Business without relief: ${MonkeyInTheMiddle("input.txt").processPart2(10000)}")
        }
    }
}
