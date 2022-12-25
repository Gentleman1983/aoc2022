package de.havox_design.aoc2022.day25

import kotlin.math.*

class FullOfHotAir(private var filename: String) {
    val data = readFile()

    fun processPart1(): Long {
        val decimalData = data
            .map { snafuNumber -> SNAFUNumber.toLong(snafuNumber) }

        return decimalData.sum()
    }

    private fun readFile() =
        getResourceAsText(filename)
            .map { snafuValue -> SNAFUNumber.toSnafu(snafuValue) }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    private fun Long.pow(e: Int): Long = Math.pow(this.toDouble(), e.toDouble()).toLong()
}
