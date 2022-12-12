package de.havox_design.aoc2022.day12

class Landscape(x: Int, y: Int, landscapeData: List<String> = emptyList()) {
    val map = init(x, y, landscapeData)

    companion object {
        private fun init(x: Int, y: Int, importData: List<String>): List<List<Field>> {
            val data = emptyList<List<Field>>().toMutableList()

            for (rowIndex in 0 until y) {
                val currentRow = emptyList<Field>().toMutableList()

                for (colIndex in 0 until x) {
                    currentRow += Field()
                }

                data += currentRow
            }

            for (rowIndex in importData.indices) {
                val currentRow = importData[rowIndex]

                for (colIndex in currentRow.indices) {
                    data[rowIndex][colIndex].elevation = currentRow.substring(colIndex, colIndex + 1)
                }
            }

            return data
        }
    }
}