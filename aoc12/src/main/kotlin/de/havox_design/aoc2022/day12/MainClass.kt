package de.havox_design.aoc2022.day12

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Steps to move: ${HillClimbingAlgorithm("input.txt").processPart1()}")
            println("Optimized steps to move: ${HillClimbingAlgorithm("input.txt").processPart2()}")
        }
    }
}
