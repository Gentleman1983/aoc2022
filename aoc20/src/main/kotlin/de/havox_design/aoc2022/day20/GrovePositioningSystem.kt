package de.havox_design.aoc2022.day20

class GrovePositioningSystem(private var filename: String) {
    val data = readFile()

    fun processPart1(): Int {
        val workingData = data.toTypedArray()
        workingData.mix()
        val startElement = workingData.indexOfFirst { it.value == 0 }
        check(startElement >= 0)

        val element1000 = workingData.getNthElement(startElement, 1000)
        val element2000 = workingData.getNthElement(startElement, 2000)
        val element3000 = workingData.getNthElement(startElement, 3000)

        println("Part 1: Data peek: start=$startElement, 1000th=$element1000, 2000th=$element2000, 3000th=$element3000")
        return element1000 + element2000 + element3000
    }

    fun processPart2(): Int =
        0

    private fun readFile() =
        getResourceAsText(filename).map { row -> Element(row.toInt()) }

    private fun Array<Element>.getNthElement(startElement: Int, n: Int): Int =
        this[(startElement + n) % this.size].value

    private fun Array<Element>.mix() {
        for (node in data) {
            val i = indexOf(node)
            check(i >= 0)
            val j = (i + node.value).mod(lastIndex)
            check(j >= 0)
            copyInto(this, minOf(i, j + 1), minOf(i + 1, j), maxOf(i, j + 1))
            this[j] = node
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
