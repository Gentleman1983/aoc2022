package de.havox_design.aoc2022.day08

class Wood {
    private var rows = emptyList<List<Tree>>().toMutableList()

    fun addRow(row: List<Tree>) {
        rows += row
    }

    fun addRow(row: String) {
        var trees = emptyList<Tree>().toMutableList()

        for (index in row.indices) {
            trees += Tree(row.substring(index, index + 1).toInt())
        }

        addRow(trees)
    }

    fun getRows(): Int = rows.size
    fun getCols(): Int =
        if (rows.isEmpty()) {
            0
        } else {
            rows[0].size
        }

    fun getTree(row: Int, col: Int): Tree = rows[row][col]

    fun isTreeVisible(row: Int, col: Int): Boolean =
        isTreeVisibleFrom(row, col, Direction.NORTH) ||
                isTreeVisibleFrom(row, col, Direction.EAST) ||
                isTreeVisibleFrom(row, col, Direction.SOUTH) ||
                isTreeVisibleFrom(row, col, Direction.WEST)

    fun isTreeVisibleFrom(row: Int, col: Int, direction: Direction): Boolean =
        when (direction) {
            Direction.NORTH -> isTreeVisibleFromColumn(row, col, 0, row)
            Direction.SOUTH -> isTreeVisibleFromColumn(row, col, row + 1, getRows())
            Direction.WEST -> isTreeVisibleFromRow(row, col, 0, col)
            Direction.EAST -> isTreeVisibleFromRow(row, col, col + 1, getCols())
        }

    private fun isTreeVisibleFromColumn(row: Int, col: Int, start: Int, end: Int): Boolean {
        var visible = true

        for (index in start until end) {
            visible = getTree(index, col).height < getTree(row, col).height && visible
        }

        return visible
    }

    private fun isTreeVisibleFromRow(row: Int, col: Int, start: Int, end: Int): Boolean {
        var visible = true

        for (index in start until end) {
            visible = getTree(row, index).height < getTree(row, col).height && visible
        }

        return visible
    }
}
