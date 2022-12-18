package de.havox_design.aoc2022.day17

data class Position(val x: Int, val y: Int) {
    operator fun plus(other: Position): Position =
        Position(x + other.x, y + other.y)

    companion object {
        fun getPositionForJet(jet: Jet): Position =
            when {
                jet == Jet.LEFT -> Position(-1, 0)
                jet == Jet.RIGHT -> Position(1, 0)
                jet == Jet.DOWN -> Position(0, -1)
                else -> Position(0, 0)
            }
    }
}
