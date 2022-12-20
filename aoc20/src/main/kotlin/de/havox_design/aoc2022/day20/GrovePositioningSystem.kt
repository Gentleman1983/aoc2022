package de.havox_design.aoc2022.day20

class GrovePositioningSystem(private var filename: String) {
    val data = readFile()

    fun processPart1(): Long =
        processPartX(1)

    fun processPart2(): Long {
        val decryptionKey = 811589153L
        return processPartX(2, decryptionKey, 10)
    }

    private fun processPartX(partNo: Int, decryptionKey: Long = 1L, numberOfMixerRuns: Int = 1): Long {
        val workingData = data.toTypedArray()
        repeat(numberOfMixerRuns) {
            workingData.mix(decryptionKey)
        }
        val startElement = workingData.indexOfFirst { it.value == 0 }
        check(startElement >= 0)

        val element1000 = workingData.getNthElement(startElement, 1000)
        val element2000 = workingData.getNthElement(startElement, 2000)
        val element3000 = workingData.getNthElement(startElement, 3000)

        println("Part $partNo: Data peek: start=$startElement, 1000th=$element1000, 2000th=$element2000, 3000th=$element3000")
        return decryptionKey * (element1000 + element2000 + element3000)
    }

    private fun readFile() =
        getResourceAsText(filename).map { row -> Element(row.toInt()) }

    private fun Array<Element>.getNthElement(startElement: Int, n: Int): Int =
        this[(startElement + n) % this.size].value

    private fun Array<Element>.mix(decryptionKey: Long = 1L) {
        for (node in data) {
            val i = indexOf(node)
            check(i >= 0)
            val j = (i + node.value * decryptionKey).mod(lastIndex)
            check(j >= 0)
            copyInto(this, minOf(i, j + 1), minOf(i + 1, j), maxOf(i, j + 1))
            this[j] = node
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
