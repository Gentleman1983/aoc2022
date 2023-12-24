package de.havox_design.aoc2022.meili

import java.lang.Exception

class MeiliSearch(private var filename: String) {
    val data = readFile()
    private val way = emptyMap<String, WayInformation>().toMutableMap()
    private var currentChild = ""

    fun processPart1(): String {
        val tree = Node.treeOf(data)
        val distanceMap = emptyMap<String, WayInformation>().toMutableMap()

        for (kid in data.keys) {
            val node = tree.find(kid).first()
            val path = node.detectPath()
            val hops = node.detectHops()
            distanceMap[kid] = WayInformation(path, hops, node)
        }

        var currentMinimalPathLength = Int.MAX_VALUE
        for (info in distanceMap.values) {
            if (info.hops < currentMinimalPathLength) {
                currentMinimalPathLength = info.hops
            }
        }

        var possibleCandidates = distanceMap.filter { (_, info) -> info.hops == currentMinimalPathLength }

        var optimalWay = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"
        for (info in possibleCandidates.values) {
            val compressedPath = compressPath(info.path)
            if (compressedPath < optimalWay) {
                optimalWay = compressedPath
            }
        }

        possibleCandidates =
            possibleCandidates.filter { (_, info) -> compressPath(info.path) == optimalWay }

        return possibleCandidates.keys.min()
    }

    @SuppressWarnings("kotlin:S6611")
    fun processPart2(args: Array<String>): Int {
        var neededHops = 0

        val tree = Node.treeOf(data)
        val distanceMap = emptyMap<String, WayInformation>().toMutableMap()

        for (kid in data.keys) {
            val node = tree.find(kid).first()
            val path = node.detectPath()
            val hops = node.detectHops()
            distanceMap[kid] = WayInformation(path, hops, node)
        }


        currentChild = if (args.contains("testing")) processPart1() else "bearach"
        val currentHops = distanceMap[currentChild]!!.hops
        val currentPath = distanceMap[currentChild]!!.path
        way[currentChild] = WayInformation(hops = currentHops, path = currentPath)
        var children = distanceMap.keys.filter { kid -> !way.containsKey(kid) }

        while (children.isNotEmpty()) {
            if (children.size % 100 == 0) {
                println("${children.size}/${data.keys.size} to process")
            }

            findNextChild(currentChild, distanceMap, children)

            children = distanceMap.keys.filter { kid -> !way.containsKey(kid) }
        }

        for (entry in way) {
            neededHops += entry.value.hops
        }

        return neededHops
    }

    @SuppressWarnings("kotlin:S6611")
    private fun findNextChild(
        currentChild: String,
        distanceMap: Map<String, WayInformation>,
        children: List<String>
    ) {
        val currentChildPath = distanceMap[currentChild]!!.path
        var nextChild = ""
        var nextChildPath = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"
        var nextChildHops = Int.MAX_VALUE

        for (child in children) {
            val childPath = distanceMap[child]!!.path
            val pathToChild = computePathToChild(childPath, currentChildPath)
            val pathToChildHops = computeHopsToChild(pathToChild)

            if (pathToChildHops < nextChildHops
                || (pathToChildHops == nextChildHops
                        && compressPath(distanceMap[child]!!.path) < compressPath(
                    if (nextChild.isEmpty()) {
                        nextChildPath
                    } else {
                        distanceMap[nextChild]!!.path
                    }
                ))
            ) {
                nextChild = child
                nextChildPath = pathToChild
                nextChildHops = pathToChildHops
            }
        }

        this.currentChild = nextChild
        way[nextChild] = WayInformation(path = nextChildPath, hops = nextChildHops)
    }

    private fun computeHopsToChild(pathToChild: String): Int =
        pathToChild.split(" - ").size - 1

    @SuppressWarnings("kotlin:S6511")
    private fun computePathToChild(childPath: String, currentChildPath: String): String {
        if (childPath == currentChildPath) {
            return ""
        } else if (childPath.contains(currentChildPath)) {
            return childPath.replace("$currentChildPath", "")
        } else if (currentChildPath.contains(childPath)) {
            return currentChildPath.replace("$childPath", "")
        }

        val childPathSteps = childPath.split(" - ")
        val currentPathSteps = currentChildPath.split(" - ")
        var path = ""

        var firstNodeDifferent = 0
        try {
            while (childPathSteps[firstNodeDifferent] == currentPathSteps[firstNodeDifferent]) {
                firstNodeDifferent++
            }
        } catch (e: Exception) {
            // ignore
        }

        for (index in firstNodeDifferent until currentPathSteps.size) {
            if (index != firstNodeDifferent) {
                path = " - $path"
            }
            path = "${currentPathSteps[index]}$path"
        }
        for (index in firstNodeDifferent - 1 until childPathSteps.size) {
            path = "$path - ${childPathSteps[index]}"
        }

        return path
    }

    private fun compressPath(path: String): String =
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
