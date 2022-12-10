package de.havox_design.aoc2022.day09

enum class Direction(var modRow: Int, var modCol: Int, var symbol: String) {
    UP_LEFT(-1, -1, "UL"),
    UP(-1, 0, "U"),
    UP_RIGHT(-1, 1, "UR"),
    RIGHT(0, 1, "R"),
    DOWN_RIGHT(1, 1, "DR"),
    DOWN(1, 0, "D"),
    DOWN_LEFT(1, -1, "DL"),
    LEFT(0, -1, "L"),
    UNKNOWN(0, 0, "UNKNOWN");

    companion object {
        fun findDirectionBySymbol(symbol: String): Direction {
            for (direction in values()) {
                if (direction.symbol == symbol.uppercase()) {
                    return direction
                }
            }

            return UNKNOWN
        }

        fun findDirectionByMovement(rowDiff: Int, colDiff: Int): Direction {
            for (direction in values()) {
                if (direction.modRow == rowDiff && direction.modCol == colDiff) {
                    return direction
                }
            }

            return UNKNOWN
        }
    }
}