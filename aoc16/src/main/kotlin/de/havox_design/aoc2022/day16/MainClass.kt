package de.havox_design.aoc2022.day16

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Pressure: ${ProboscideaVolcanium("input.txt").processPart1()}")

            if(!args.contains("testing")) {
                println("Pressure (elephant helping): ${ProboscideaVolcanium("input.txt").processPart2()}")
            }
        }
    }
}
