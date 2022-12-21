package de.havox_design.aoc2022.meili

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            if (!args.contains("testing")) {
                println("Kid: ${MeiliSearch("input.txt").processPart1()}")
                println("Number of hops: ${MeiliSearch("input.txt").processPart2(args)}")
            }
        }
    }
}
