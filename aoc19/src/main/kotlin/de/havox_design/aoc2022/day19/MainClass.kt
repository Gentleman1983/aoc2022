package de.havox_design.aoc2022.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if(!args.contains("testing")) {
                println("Sum of all quality levels: ${NotEnoughMinerals("input.txt").processPart1()}")
                println("???: ${NotEnoughMinerals("input.txt").processPart2()}")
            }
        }
    }
}
