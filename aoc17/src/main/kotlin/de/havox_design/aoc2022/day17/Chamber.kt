package de.havox_design.aoc2022.day17

class Chamber(var width: Long = 7L) {
    var obstacles: Set<Position> = emptySet<Position>().toMutableSet()

    fun getMaxHeight(): Long =
        obstacles.maxOfOrNull { position -> position.y + 1 } ?: 0

    fun getStartPositionForRock(
        distanceToLeftWall: Long = 2,
        distanceToHeighestObstacle: Long = 3
    ): Position {
        val maxHeight = getMaxHeight()

        return Position(distanceToLeftWall, maxHeight + distanceToHeighestObstacle)
    }

    fun addRockToObstaclesIfItCollides(rock: Rock, position: Position, direction: Jet): Boolean {
        val rockBlockers = rock
            .getBlockedPositions()
            .map { pos -> pos + position }
            .toList()
        val realDirection = getRealDirection(rock, position, direction)

        // Do we collide with floor or another stone?
        if (
            rockBlockers
                .map { pos -> pos + Position.getPositionForJet(realDirection) }
                .any { pos -> pos.y < 0 || obstacles.contains(pos) }
        ) {
            obstacles += rockBlockers
            return true
        }

        return false
    }

    fun getRealDirection(rock: Rock, position: Position, direction: Jet): Jet {
        var realDirection = direction
        val rockBlockers = rock
            .getBlockedPositions()
            .map { pos -> pos + position }
            .toList()

        // We cannot be blown out of the tunnel
        if (
            (
                    direction == Jet.LEFT
                            && (
                            rockBlockers
                                .any { pos -> pos.x == 0L }
                                    || rockBlockers
                                .map { pos -> pos + Position.getPositionForJet(Jet.LEFT) }
                                .any { pos -> obstacles.contains(pos) }
                            )
                    )
            || (
                    direction == Jet.RIGHT
                            && (
                            rockBlockers
                                .any { pos -> pos.x == width - 1 }
                                    || rockBlockers
                                .map { pos -> pos + Position.getPositionForJet(Jet.RIGHT) }
                                .any { pos -> obstacles.contains(pos) }
                            )
                    )
        ) {
            realDirection = Jet.UNKNOWN
        }

        return realDirection
    }
}