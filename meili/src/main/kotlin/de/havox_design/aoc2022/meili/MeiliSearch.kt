package de.havox_design.aoc2022.meili

class MeiliSearch(private var filename: String) {
    val data = readFile()

    fun processPart1(): String =
        "0"

    fun processPart2(): Int =
        0

    private fun readFile() =
        getResourceAsText(filename)

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
