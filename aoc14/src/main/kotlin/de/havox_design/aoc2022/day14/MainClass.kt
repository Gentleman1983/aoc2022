package de.havox_design.aoc2022.day14

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Units of sand: ${RegolithReservoir("input.txt").processPart1()}")
            println("???: ${RegolithReservoir("input.txt").processPart2()}")
        }
    }
}
