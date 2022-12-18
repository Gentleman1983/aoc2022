package de.havox_design.aoc2022.day13

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Sum of indices with right order: ${DistressSignal("input.txt").processPart1()}")
            println("Decoder key: ${DistressSignal("input.txt").processPart2()}")
        }
    }
}
