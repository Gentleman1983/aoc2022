package de.havox_design.aoc2022.day04

class CampCleanup(private val filename: String) {
    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}