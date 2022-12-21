package de.havox_design.aoc2022.meili

class MeiliSearch(private var filename: String) {
    val data = readFile()

    fun processPart1(): String {
        val tree = Node.treeOf(data)

        return "0"
    }

    fun processPart2(): Int =
        0

    private fun readFile(): Map<String, String> {
        val dataMap = emptyMap<String, String>().toMutableMap()
        val fileData = getResourceAsText(filename)

        for(row in fileData) {
            val data = row.split(" - ")
            val kid = data[0]
            val path = data[1]
            dataMap[kid] = path
        }

        return dataMap
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
