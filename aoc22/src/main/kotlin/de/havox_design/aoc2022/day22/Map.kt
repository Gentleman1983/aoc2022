package de.havox_design.aoc2022.day22

class Map(var import: List<String>) {
    private var map = buildMap()
    var currentPosition = Position(0, 0)

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

    fun tryMoveUp() =
        tryMove(targetX = currentPosition.x, targetY = currentPosition.y - 1, targetDirection = Field.UP)

    fun tryMoveRight() =
        tryMove(targetX = currentPosition.x + 1, targetY = currentPosition.y, targetDirection = Field.RIGHT)

    fun tryMoveDown() =
        tryMove(targetX = currentPosition.x, targetY = currentPosition.y + 1, targetDirection = Field.DOWN)

    fun tryMoveLeft() =
        tryMove(targetX = currentPosition.x - 1, targetY = currentPosition.y, targetDirection = Field.LEFT)

    private fun tryMove(targetX: Int, targetY: Int, targetDirection: Field) {
        val targetField = map[targetY][targetX]

        if (targetField != Field.BLOCKED && targetField != Field.UNAVAILABLE) {
            map[targetY][targetX] = targetDirection
            currentPosition = Position(targetX, targetY)
        }
    }

    private fun buildMap(): Array<Array<Field>> {
        val columns = import[0].length

        val rows = emptyArray<Array<Field>>()
        var currentRow = emptyArray<Field>()
        for (col in 0..columns + 1) {
            currentRow[col] = Field.UNAVAILABLE
        }
        rows[0] = currentRow

        for (row in 1..import.size) {
            currentRow = emptyArray<Field>()
            currentRow[0] = Field.UNAVAILABLE

            for (col in 1..columns) {
                when (import[row - 1][col - 1].toString()) {
                    Field.FREE.symbol -> currentRow[col] = Field.FREE
                    Field.BLOCKED.symbol -> currentRow[col] = Field.BLOCKED
                    else -> currentRow[col] = Field.UNAVAILABLE
                }
            }
            currentRow[columns + 1] = Field.UNAVAILABLE
            rows[row] = currentRow
        }

        currentRow = emptyArray<Field>()
        for (col in 0..columns + 1) {
            currentRow[col] = Field.UNAVAILABLE
        }
        rows[import.size + 1] = currentRow

        return rows
    }
}
