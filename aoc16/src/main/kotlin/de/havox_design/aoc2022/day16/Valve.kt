package de.havox_design.aoc2022.day16

data class Valve(val name: String, val rate: Int, val next: List<String>) {
    companion object {
        fun from(line: String): Valve {
            return Valve(
                name = line
                    .substringAfter("Valve ")
                    .substringBefore(" "),
                rate = line
                    .substringAfter("rate=")
                    .substringBefore(";")
                    .toInt(),
                next = line
                    .substringAfter("to valve")
                    .substringAfter(" ")
                    .split(", ")
            )
        }
    }
}
