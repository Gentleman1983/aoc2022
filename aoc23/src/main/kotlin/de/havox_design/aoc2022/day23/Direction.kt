package de.havox_design.aoc2022.day23

enum class Direction(val symbol: String, val direction: Pair<Int, Int>) {
    NORTH_WEST("NW", Pair(-1, -1)),
    NORTH("N", Pair(-1, 0)),
    NORTH_EAST("NE", Pair(-1, 1)),
    EAST("E", Pair(0, 1)),
    SOUTH_EAST("SE", Pair(1, 1)),
    SOUTH("S", Pair(1, 0)),
    SOUTH_WEST("SW", Pair(1, -1)),
    WEST("W", Pair(0, -1));

    override fun toString(): String {
        return symbol
    }
}