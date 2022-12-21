package de.havox_design.aoc2022.meili

import java.lang.Integer.min

class MeiliSearch(private var filename: String) {
    val data = readFile()

    fun processPart1(): String {
        val tree = Node.treeOf(data)
        val distanceMap = emptyMap<String, WayInformation>().toMutableMap()

        for (kid in data.keys) {
            val node = tree.find(kid).first()
            val path = node.detectPath()
            val hops = node.detectHops()
            distanceMap[kid] = WayInformation(path, hops)
        }

        var currentMinimalPathLength = Int.MAX_VALUE
        for (info in distanceMap.values) {
            if (info.hops < currentMinimalPathLength) {
                currentMinimalPathLength = info.hops
            }
        }

        var possibleCandidates = distanceMap.filter { (kids, info) -> info.hops == currentMinimalPathLength }

        for (entry in possibleCandidates) {
            println(entry)
        }

        var optimalWay = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"
        for (info in possibleCandidates.values) {
            val compressedPath = compressPath(info.path)
            if(compressedPath < optimalWay) {
                optimalWay = compressedPath
            }
        }

        possibleCandidates =
            possibleCandidates.filter { (kids, info) -> compressPath(info.path) == optimalWay }

        println("L < R: ${"L"<"R"}")
        println("L < Z: ${"L"<"Z"}")
        println("R < Z: ${"R"<"Z"}")
        println(possibleCandidates.keys.size)
        println(compressPath(possibleCandidates.values.first().path))

        return possibleCandidates.keys.min()
    }

    fun processPart2(): Int =
        0

    fun compressPath(path: String): String =
        path.replace(" - ", "").replace("root", "")

    private fun readFile(): Map<String, String> {
        val dataMap = emptyMap<String, String>().toMutableMap()
        val fileData = getResourceAsText(filename)

        for (row in fileData) {
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
