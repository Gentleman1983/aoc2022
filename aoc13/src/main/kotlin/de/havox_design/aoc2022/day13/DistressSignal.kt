package de.havox_design.aoc2022.day13

class DistressSignal(private var filename: String) {
    val packetList = readFile()

    fun processPart1(): Int =
        packetList
            .chunked(2) { (firstPacket, secondPacket) -> firstPacket <= secondPacket }
            .withIndex()
            .filter { currentPacket -> currentPacket.value }
            .sumOf { currentPacket -> currentPacket.index + 1 }

    fun processPart2(): Int {
        var positionOfLowerDecoderPacket = 1
        var positionOfUpperDecoderPacket = 1
        for (packet in packetList) {
            when {
                packet < Packet.decoderPackets[0] -> positionOfLowerDecoderPacket++
                packet < Packet.decoderPackets[1] -> positionOfUpperDecoderPacket++
            }
        }
        return positionOfLowerDecoderPacket * (positionOfLowerDecoderPacket + positionOfUpperDecoderPacket)
    }

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
