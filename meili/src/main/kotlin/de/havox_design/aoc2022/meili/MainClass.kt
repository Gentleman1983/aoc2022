package de.havox_design.aoc2022.meili

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Kid: ${MeiliSearch("input.txt").processPart1()}")
            println("???: ${MeiliSearch("input.txt").processPart2()}")
        }
    }
}
