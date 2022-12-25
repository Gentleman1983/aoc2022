package de.havox_design.aoc2022.day25

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("SNAFU number: ${FullOfHotAir("input.txt").processPart1()}")
            println("???: ${FullOfHotAir("input.txt").processPart2()}")
        }
    }
}
