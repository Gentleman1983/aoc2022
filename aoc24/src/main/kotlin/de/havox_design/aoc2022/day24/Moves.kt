package de.havox_design.aoc2022.day24

enum class Moves(private val function: (Pair<Int, Int>) -> Pair<Int, Int>) {
    WAIT({ pos -> Position(pos.getX(), pos.getY()) }),
    NORTH({ pos -> Position(pos.getX(), pos.getY() - 1) }),
    EAST({ pos -> Position(pos.getX() + 1, pos.getY()) }),
    SOUTH({ pos -> Position(pos.getX(), pos.getY() + 1) }),
    WEST({ pos -> Position(pos.getX() - 1, pos.getY()) });

    fun getDirection(pos: Position): Position =
        function(pos)
}

private fun Pair<Int, Int>.getX(): Int = this.first
private fun Pair<Int, Int>.getY(): Int = this.second
