package de.havox_design.aoc2022.day15

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Number of positions: ${BeaconExclusionZone("input.txt").processPart1(2000000)}")
            println("Tuning frequency: ${BeaconExclusionZone("input.txt").processPart2(4000000)}")
        }
    }
}
