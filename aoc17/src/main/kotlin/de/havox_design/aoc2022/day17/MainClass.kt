package de.havox_design.aoc2022.day17

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Tower size: ${PyroclasticFlow("input.txt").processPart1()}")
            println("???: ${PyroclasticFlow("input.txt").processPart2()}")
        }
    }
}
