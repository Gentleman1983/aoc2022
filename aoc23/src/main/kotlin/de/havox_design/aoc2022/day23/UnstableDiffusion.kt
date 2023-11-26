package de.havox_design.aoc2022.day23

import de.havox_design.aoc2022.day23.Direction.*

class UnstableDiffusion(private var filename: String) {
    private var data = emptySet<Pair<Int, Int>>().toMutableSet()
    private val directions = mutableListOf(
        listOf(NORTH_WEST, NORTH, NORTH_EAST),
        listOf(SOUTH_WEST, SOUTH, SOUTH_EAST),
        listOf(NORTH_WEST, WEST, SOUTH_WEST),
        listOf(NORTH_EAST, EAST, SOUTH_EAST)
    )

    fun processPart1(endInRound: Int = 10): Int {
        data = readFile()

        processElves(endInRound)

        // detect rectangle data
        val minY = data.minOf { pos -> pos.getY() }
        val minX = data.minOf { pos -> pos.getX() }
        val maxY = data.maxOf { pos -> pos.getY() }
        val maxX = data.maxOf { pos -> pos.getX() }

        return (maxY - minY + 1) * (maxX - minX + 1) - data.size
    }

    fun processPart2(): Int {
        data = readFile()

        return processElves()
    }

    @SuppressWarnings("kotlin:S6611")
    private fun processElves(endInRound: Int = Int.MAX_VALUE): Int {
        var round = 0

        while (true) {
            val proposals: MutableMap<Point, MutableList<Point>> = mutableMapOf()
            for ((y, x) in data) {
                val checkFields = directions.map { checkDirections ->
                    checkDirections.map { direction ->
                        Point(y + direction.getY(), x + direction.getX())
                    }
                }
                if (
                    checkFields
                        .flatten()
                        .count { data.contains(it) } > 0
                ) {
                    for (directions in checkFields) {
                        if (directions.count { data.contains(it) } > 0) {
                            continue
                        }
                        if (directions.getDirection() !in proposals) {
                            proposals[directions.getDirection()] = mutableListOf()
                        }
                        proposals[directions.getDirection()]!!.add(Point(y, x))
                        break
                    }
                }
            }
            for ((point, candidates) in proposals) {
                if (candidates.size == 1) {
                    data.remove(candidates[0])
                    data.add(point)
                }
            }

            directions.add(directions.removeAt(0))

            round++

            if (round == endInRound || proposals.isEmpty()) {
                break
            }
        }

        return round
    }

    private fun readFile() =
        getResourceAsText(filename)
            .flatMapIndexed { y, row ->
                row.mapIndexed { x, value -> Pair(Point(y, x), value) }
            }
            .filter { it.getSymbol() == '#' }
            .map { it.getPosition() }
            .toMutableSet()

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}

private fun Pair<Pair<Int, Int>, Char>.getPosition(): Pair<Int, Int> =
    this.first

private fun Pair<Pair<Int, Int>, Char>.getSymbol(): Char =
    this.second

private fun Pair<Int, Int>.getX(): Int =
    this.second

private fun Pair<Int, Int>.getY(): Int =
    this.first

private fun Direction.getX(): Int =
    this.direction.getX()

private fun Direction.getY(): Int =
    this.direction.getY()

private fun List<Point>.getDirection(): Point =
    this[1]