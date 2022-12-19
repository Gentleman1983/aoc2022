package de.havox_design.aoc2022.day19

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if(!args.contains("testing")) {
                val instance = NotEnoughMinerals("input.txt")
                println("Sum of all quality levels: ${instance.processPart1()}")
                println("Product: ${instance.processPart2()}")
            }
        }
    }
}
