package de.havox_design.aoc2022.day09

import kotlin.math.abs

data class Grid(var rows: Int, var cols: Int) {
    private var data = init()
    private var posHead = arrayOf(-1, -1)
    private var posTail = arrayOf(-1, -1)

    fun getPosition(row: Int, col: Int): Position =
        data[row][col]

    fun visitPosition(row: Int, col: Int, knot: Knot) {
        getPosition(row, col).visit(knot)

        if (Knot.HEAD == knot || Knot.HEAD_AND_TAIL == knot) {
            posHead[ROW_INDEX] = row
            posHead[COL_INDEX] = col

            if (posTail[ROW_INDEX] != -1 && posTail[COL_INDEX] != -1) {
                checkMoveOfTailNeeded()
            }
        }

        if (Knot.TAIL == knot || Knot.HEAD_AND_TAIL == knot) {
            posTail[ROW_INDEX] = row
            posTail[COL_INDEX] = col
        }
    }

    fun moveHead(move: Move): Direction {
        val oldTailRow = posTail[ROW_INDEX]
        val oldTailCol = posTail[COL_INDEX]

        move(posHead[ROW_INDEX], posHead[COL_INDEX], move)

        return Direction.findDirectionByMovement(
            posTail[ROW_INDEX] - oldTailRow,
            posTail[COL_INDEX] - oldTailCol
        )
    }

    fun move(row: Int, col: Int, move: Move) {
        move(row, col, move.direction, move.numberOfFields)
    }

    fun move(row: Int, col: Int, direction: Direction, numberOfFields: Int) {
        for (move in 0 until numberOfFields) {
            move(row + move * direction.modRow, col + move * direction.modCol, direction)
        }
    }

    fun move(row: Int, col: Int, direction: Direction) {
        val targetRow = row + direction.modRow
        val targetCol = col + direction.modCol

        move(row, col, targetRow, targetCol)
    }

    private fun move(row: Int, col: Int, targetRow: Int, targetCol: Int) {
        var knot = getPosition(row, col).knot
        if (Knot.HEAD_AND_TAIL == knot) {
            knot = Knot.HEAD
        }

        if (knot != null) {
            getPosition(row, col).leave()
            visitPosition(targetRow, targetCol, knot)
        }
    }

    private fun checkMoveOfTailNeeded() {
        val rowDifference = posHead[ROW_INDEX] - posTail[ROW_INDEX]
        val colDifference = posHead[COL_INDEX] - posTail[COL_INDEX]
        if (abs(rowDifference) > 1 || abs(colDifference) > 1) {
            getPosition(posTail[ROW_INDEX], posTail[COL_INDEX]).leave()

            if (rowDifference == 0) {
                posTail[COL_INDEX] += colDifference / abs(colDifference)
            } else if (colDifference == 0) {
                posTail[ROW_INDEX] += rowDifference / abs(rowDifference)
            } else {
                posTail[COL_INDEX] += colDifference / abs(colDifference)
                posTail[ROW_INDEX] += rowDifference / abs(rowDifference)
            }

            visitPosition(posTail[ROW_INDEX], posTail[COL_INDEX], Knot.TAIL)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        return data.hashCode()
    }

    override fun toString(): String {
        return "Grid(rows=$rows, cols=$cols, data=$data, posHead=${posHead.contentToString()}, posTail=${posTail.contentToString()})"
    }

    private fun init(): List<List<Position>> {
        val initialData = emptyList<List<Position>>().toMutableList()

        for (rowIndex in 0 until rows) {
            val currentRow = emptyList<Position>().toMutableList()

            for (colIndex in 0 until cols) {
                currentRow.add(Position())
            }

            initialData.add(currentRow)
        }

        return initialData
    }

    companion object {
        private var ROW_INDEX = 0
        private var COL_INDEX = 1
    }
}