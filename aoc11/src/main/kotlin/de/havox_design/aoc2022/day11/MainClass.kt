package de.havox_design.aoc2022.day11

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            main(false)
        }

        @JvmStatic
        fun main(runningTests: Boolean) {
            println("Monkey Business: ${MonkeyInTheMiddle("input.txt").processPart1(20)}")
            val numberOfRuns = if (runningTests) 100 else 10000
            println("If running many iterations this may take some time... Current runs: $numberOfRuns")
            println("Monkey Business without relief: ${MonkeyInTheMiddle("input.txt").processPart2(numberOfRuns)}")
        }
    }
}
