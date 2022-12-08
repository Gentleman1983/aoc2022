package de.havox_design.aoc2022.day08

class TreetopTreeHouse(private var filename: String) {
    fun readFile() {
        getResourceAsText(filename)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}