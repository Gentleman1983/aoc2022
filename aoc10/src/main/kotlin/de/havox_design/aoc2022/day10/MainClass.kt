package de.havox_design.aoc2022.day10

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Signal Strength: ${CathodeRayTube("input.txt").processPart1()}")
            println("CRT preview:")
            println("===========")
            println(CathodeRayTube("input.txt").processPart2())
        }
    }
}
