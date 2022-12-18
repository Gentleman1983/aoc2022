package de.havox_design.aoc2022.day17

enum class Rock(val dimensionX: Long, val dimensionY: Long, private val structure: Map<Position, String>) {
    HORIZONTAL_LINE(
        4,
        1,
        mapOf(
            Pair(Position(0,0), "#"),
            Pair(Position(1,0), "#"),
            Pair(Position(2,0), "#"),
            Pair(Position(3,0), "#")
        )
    ),
    PLUS(
        3,
        3,
        mapOf(
            Pair(Position(0,2), "."),
            Pair(Position(1,2), "#"),
            Pair(Position(2,2), "."),
            Pair(Position(0,1), "#"),
            Pair(Position(1,1), "#"),
            Pair(Position(2,1), "#"),
            Pair(Position(0,0), "."),
            Pair(Position(1,0), "#"),
            Pair(Position(2,0), ".")
        )
    ),
    ARROW(
        3,
        3,
        mapOf(
            Pair(Position(0,2), "."),
            Pair(Position(1,2), "."),
            Pair(Position(2,2), "#"),
            Pair(Position(0,1), "."),
            Pair(Position(1,1), "."),
            Pair(Position(2,1), "#"),
            Pair(Position(0,0), "#"),
            Pair(Position(1,0), "#"),
            Pair(Position(2,0), "#")
        )
    ),
    VERTICAL_LINE(
        1,
        4,
        mapOf(
            Pair(Position(0,3), "#"),
            Pair(Position(0,2), "#"),
            Pair(Position(0,1), "#"),
            Pair(Position(0,0), "#")
        )
    ),
    BOX(
        2,
        2,
        mapOf(
            Pair(Position(0,1), "#"),
            Pair(Position(1,1), "#"),
            Pair(Position(0,0), "#"),
            Pair(Position(1,0), "#")
        )
    );

    fun getBlockedPositions(): Set<Position> =
        structure
            .entries
            .filter { entry -> entry.value == "#" }
            .map { entry -> entry.key }
            .toSet()
}