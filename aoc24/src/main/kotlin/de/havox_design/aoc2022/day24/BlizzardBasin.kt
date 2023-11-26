package de.havox_design.aoc2022.day24

import java.util.*
import kotlin.math.abs
import de.havox_design.aoc2022.day24.Moves.*

class BlizzardBasin(private var filename: String) {
    private val data = readFile()

    fun processPart1(): Int =
        findWay(getStart(), getEnd())

    fun processPart2(): Array<Int> {
        val timeAfterSearch1 = findWay(getStart(), getEnd())
        val timeAfterSearch2 = findWay(getEnd(), getStart(), timeAfterSearch1)
        val timeAfterSearch3 = findWay(getStart(), getEnd(), timeAfterSearch2)

        return arrayOf(
            timeAfterSearch1,
            timeAfterSearch2 - timeAfterSearch1,
            timeAfterSearch3 - timeAfterSearch2,
            timeAfterSearch3
        )
    }


    fun getStart(): Position =
        Position(data.first().indexOf('.'), 0)

    fun getEnd(): Position =
        Position(data.last().lastIndexOf('.'), data.lastIndex)

    @SuppressWarnings("kotlin:S6529")
    private fun findWay(startPosition: Position, endPosition: Position, startTime: Int = 0): Int {
        val seen = mutableSetOf(IndexedValue(startTime, startPosition))
        val queue = PriorityQueue(compareBy(IndexedValue<IndexedValue<Position>>::index))
        queue.add(IndexedValue(0, IndexedValue(startTime, startPosition)))
        while (!queue.isEmpty()) {
            val entry = queue.remove().value
            val (time, position) = entry
            if (position == endPosition) {
                return time
            }
            for (position2 in arrayOf(
                WAIT.getDirection(position),
                NORTH.getDirection(position),
                EAST.getDirection(position),
                SOUTH.getDirection(position),
                WEST.getDirection(position)
            )
            ) {
                if (!isFree(position2, time + 1)) continue
                val state = IndexedValue(time + 1, position2)
                if (seen.add(state)) queue.add(
                    IndexedValue(
                        time + abs(position2.getX() - endPosition.getX()) + abs(
                            position2.getY() - endPosition.getY()
                        ), state
                    )
                )
            }
        }
        throw NoSuchElementException()
    }

    private fun isFree(position: Position, time: Int): Boolean {
        val x = position.getX()
        val y = position.getY()

        return if (x <= 0 || y <= 0 || y >= data.lastIndex || x >= data.getOrElse(y) { "" }.lastIndex) {
            data.getOrNull(y)?.getOrNull(x) == '.'
        } else {
            data[y][(x - 1 + time).mod(data[y].length - 2) + 1] != '<' &&
                    data[y][(x - 1 - time).mod(data[y].length - 2) + 1] != '>' &&
                    data[(y - 1 + time).mod(data.size - 2) + 1][x] != '^' &&
                    data[(y - 1 - time).mod(data.size - 2) + 1][x] != 'v'
        }
    }

    private fun readFile() =
        getResourceAsText(filename)

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}

private fun Pair<Int, Int>.getX(): Int = this.first
private fun Pair<Int, Int>.getY(): Int = this.second
