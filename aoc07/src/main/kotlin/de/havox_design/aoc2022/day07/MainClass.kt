package de.havox_design.aoc2022.day07

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Total filesystem size: ${NoSpaceLeftOnDevice("input.txt").processPart1()}")
        }
    }
}
