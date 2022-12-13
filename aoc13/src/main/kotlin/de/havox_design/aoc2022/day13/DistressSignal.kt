package de.havox_design.aoc2022.day13

class DistressSignal(private var filename: String) {

    fun processPart1(): Int =
        0

    fun processPart2(): Int =
        0

    private fun readFile() {

    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}