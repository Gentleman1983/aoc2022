package de.havox_design.aoc2022.day09

import kotlin.math.abs

data class Grid(var rows: Int, var cols: Int) {
    private var data = init()
    private var posHead = arrayOf(-1, -1)
    private var posTail = arrayOf(-1, -1)
    private var ROW_INDEX = 0
    private var COL_INDEX = 1

    fun getPosition(row: Int, col: Int): Position =
        data[row][col]

    fun visitPosition(row: Int, col: Int, knot: Knot) {
        getPosition(row, col).visit(knot)

        if (Knot.HEAD == knot) {
            posHead[ROW_INDEX] = row
            posHead[COL_INDEX] = col

            if (posTail[ROW_INDEX] != -1 && posTail[COL_INDEX] != -1) {
                checkMoveOfTailNeeded()
            }
        } else if (Knot.TAIL == knot) {
            posTail[ROW_INDEX] = row
            posTail[COL_INDEX] = col
        }
    }

    fun move(row: Int, col: Int, targetRow: Int, targetCol: Int) {
        val knot = getPosition(row, col).knot

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

            getPosition(posTail[ROW_INDEX], posTail[COL_INDEX]).visit(Knot.TAIL)
        }
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
}