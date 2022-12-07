package de.havox_design.aoc2022.day07

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Total folder size: ${NoSpaceLeftOnDevice("input.txt").processPart1()}")
            println("Smallest folder size: ${NoSpaceLeftOnDevice("input.txt").processPart2()}")
        }
    }
}
