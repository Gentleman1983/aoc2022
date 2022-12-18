package de.havox_design.aoc2022.day13

class DistressSignal(private var filename: String) {
    val packetList = readFile()

    fun processPart1(): Int =
        packetList
            .chunked(2) { (firstPacket, secondPacket) -> firstPacket <= secondPacket }
            .withIndex()
            .filter { currentPacket -> currentPacket.value }
            .sumOf { currentPacket -> currentPacket.index + 1 }

    fun processPart2(): Int =
        0

    private fun readFile(): List<Packet> {
        val fileInput = getResourceAsText(filename)

        return fileInput.mapNotNull { row ->
            if (row.isNotEmpty()) {
                Packet.parsePacket(row)
            } else {
                null
            }
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}