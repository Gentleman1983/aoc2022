package de.havox_design.aoc2022.day22

import java.lang.IllegalArgumentException

class Map(private var import: List<String>) {
    var map = buildMap()
    var currentPosition = setStartPosition()

    fun turnRight() {
        when (map[currentPosition.y][currentPosition.x]) {
            Field.UP -> map[currentPosition.y][currentPosition.x] = Field.RIGHT
            Field.RIGHT -> map[currentPosition.y][currentPosition.x] = Field.DOWN
            Field.DOWN -> map[currentPosition.y][currentPosition.x] = Field.LEFT
            Field.LEFT -> map[currentPosition.y][currentPosition.x] = Field.UP
            else -> return // ignore
        }
    }

    fun turnLeft() {
        when (map[currentPosition.y][currentPosition.x]) {
            Field.UP -> map[currentPosition.y][currentPosition.x] = Field.LEFT
            Field.RIGHT -> map[currentPosition.y][currentPosition.x] = Field.UP
            Field.DOWN -> map[currentPosition.y][currentPosition.x] = Field.RIGHT
            Field.LEFT -> map[currentPosition.y][currentPosition.x] = Field.DOWN
            else -> return // ignore
        }
    }

    fun move(numberOfFields: Int) {
        when (map[currentPosition.y][currentPosition.x]) {
            Field.UP -> for (i in 0 until numberOfFields) tryMoveUp()
            Field.RIGHT -> for (i in 0 until numberOfFields) tryMoveRight()
            Field.DOWN -> for (i in 0 until numberOfFields) tryMoveDown()
            Field.LEFT -> for (i in 0 until numberOfFields) tryMoveLeft()
            else -> return // ignore
        }
    }

    fun evaluatePosition(): Int {
        val rowNo = currentPosition.y
        val colNo = currentPosition.x

        return rowNo * 1000 + colNo * 4 + map[rowNo][colNo].value
    }

    private fun tryMoveUp() =
        tryMove(targetX = currentPosition.x, targetY = currentPosition.y - 1, targetDirection = Field.UP)

    private fun tryMoveRight() =
        tryMove(targetX = currentPosition.x + 1, targetY = currentPosition.y, targetDirection = Field.RIGHT)

    private fun tryMoveDown() =
        tryMove(targetX = currentPosition.x, targetY = currentPosition.y + 1, targetDirection = Field.DOWN)

    private fun tryMoveLeft() =
        tryMove(targetX = currentPosition.x - 1, targetY = currentPosition.y, targetDirection = Field.LEFT)

    private fun tryMove(targetX: Int, targetY: Int, targetDirection: Field) {
        // Handle map borders
        if(targetY >= map.size) {
            tryMove(targetX = targetX, targetY = 1, targetDirection = targetDirection)
            return
        }
        if(targetY < 0) {
            tryMove(targetX = targetX, targetY = map.size - 1, targetDirection = targetDirection)
            return
        }
        if(targetX >= map[0].size) {
            tryMove(targetX = 1, targetY = targetY, targetDirection = targetDirection)
            return
        }
        if(targetX < 0) {
            tryMove(targetX = map[0].size - 1, targetY = targetY, targetDirection = targetDirection)
            return
        }

        val targetField = map[targetY][targetX]

        if (targetField != Field.BLOCKED && targetField != Field.UNAVAILABLE) {
            map[targetY][targetX] = targetDirection
            currentPosition = Position(targetX, targetY)
        }
        if(targetField == Field.UNAVAILABLE) {
            when(targetDirection) {
                Field.UP -> tryMove(targetX = targetX, targetY = targetY - 1, targetDirection = targetDirection)
                Field.RIGHT -> tryMove(targetX = targetX + 1, targetY = targetY, targetDirection = targetDirection)
                Field.DOWN -> tryMove(targetX = targetX, targetY = targetY + 1, targetDirection = targetDirection)
                Field.LEFT -> tryMove(targetX = targetX - 1, targetY = targetY, targetDirection = targetDirection)
                else -> throw IllegalArgumentException("This should never happen")
            }
        }
    }

    private fun setStartPosition(): Position {
        for(fieldIndex in map[1].indices) {
            val field = map[1][fieldIndex]
            if(field != Field.BLOCKED
                && field != Field.UNAVAILABLE) {
                map[1][fieldIndex] = Field.RIGHT
                return Position(y = 1, x = fieldIndex)
            }
        }

        throw IllegalArgumentException("Expected to have a valid start field in row 1.")
    }

    private fun buildMap(): Array<Array<Field>> {
        val columns = import.maxOf { row -> row.length }

        val rows = emptyList<Array<Field>>().toMutableList()
        var currentRow = emptyList<Field>().toMutableList()
        for (col in 0..columns + 1) {
            currentRow += Field.UNAVAILABLE
        }
        rows += currentRow.toTypedArray()

        for (row in 1..import.size) {
            currentRow = emptyList<Field>().toMutableList()
            currentRow += Field.UNAVAILABLE

            for (col in 1..columns) {
                if(row <= import.size &&
                    col <= import[row - 1].length) {
                    currentRow += when (import[row - 1][col - 1].toString()) {
                        Field.FREE.symbol -> Field.FREE
                        Field.BLOCKED.symbol -> Field.BLOCKED
                        else -> Field.UNAVAILABLE
                    }
                }
                else {
                    currentRow += Field.UNAVAILABLE
                }
            }
            currentRow += Field.UNAVAILABLE
            rows += currentRow.toTypedArray()
        }

        currentRow = emptyList<Field>().toMutableList()
        for (col in 0..columns + 1) {
            currentRow += Field.UNAVAILABLE
        }
        rows += currentRow.toTypedArray()

        return rows.toTypedArray()
    }
}
