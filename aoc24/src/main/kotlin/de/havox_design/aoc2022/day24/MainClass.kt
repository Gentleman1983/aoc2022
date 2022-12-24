package de.havox_design.aoc2022.day24

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Minutes needed: ${BlizzardBasin("input.txt").processPart1()}")
            println("???: ${BlizzardBasin("input.txt").processPart2()}")
        }
    }
}
