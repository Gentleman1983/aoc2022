package de.havox_design.aoc2022.day08

class TreetopTreeHouse(private var filename: String) {
    private var wood = Wood()

    fun readFile() {
        val data = getResourceAsText(filename)

        for(row in data) {
            wood.addRow(row)
        }
    }

    fun getWood():Wood = wood

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
