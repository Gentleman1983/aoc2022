package de.havox_design.aoc2022.day17

enum class Jet(private val code: String) {
    LEFT("<"),
    RIGHT(">"),
    DOWN("v"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun getJetForCode(code: String): Jet =
            values().firstOrNull() { jet -> jet.code == code } ?: UNKNOWN
    }
}