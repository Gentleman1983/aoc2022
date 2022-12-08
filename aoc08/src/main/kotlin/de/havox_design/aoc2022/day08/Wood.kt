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
}
