package de.havox_design.aoc2022.day07

class NoSpaceLeftOnDevice(private var filename: String) {
    private var filesystem: Directory = Directory(null, "/", emptySet(), emptySet())
    private var currentPosition: Directory = filesystem

    fun processPart1(): Int {
        var totalSize = 0
        val limit = 100000

        readData()
        val dirs = collectAllDirs(filesystem)

        for (dir in dirs) {
            totalSize += dir.calculateSizeOfFilesInDirAndSubDirs(limit)
        }

        return totalSize
    }

    fun processPart2(): Int {
        val totalDiskSpace = 70000000
        val updateSize = 30000000

        readData()

        val freeDiskSize = totalDiskSpace - filesystem.calculateSizeOfFilesInDirAndSubDirs()
        val neededSize = (updateSize - freeDiskSize).coerceAtLeast(0)

        return filesystem.findSmallestDirLargerThanLimit(neededSize)
    }

    private fun collectAllDirs(source: Directory): Set<Directory> {
        val dirs = setOf(source).toMutableSet()

        for (dir in source.dirs) {
            dirs += collectAllDirs(dir)
        }

        return dirs
    }


    fun getFs(): Directory = filesystem

    fun readData() {
        val fileData = getResourceAsText(filename)

        for (row in fileData) {
            if (row.startsWith("$ ")) {
                readSystemCall(row.substring(2))
            } else {
                readFileOrFolder(row)
            }
        }
    }

    private fun readFileOrFolder(call: String) {
        if (call.startsWith("dir ")) {
            val target = call.substring(4)

            if (currentPosition.dirs.none { dir -> dir.name == target }) {
                currentPosition.subDir(target)
            }
        } else {
            val elements = call.split(" ")
            val fileName = elements[1]
            val fileSize = elements[0].toInt()

            currentPosition.file(fileName, fileSize)
        }
    }

    @SuppressWarnings("kotlin:S6511")
    private fun readSystemCall(call: String) {
        if (call == "cd /") {
            currentPosition = filesystem
        } else if (call == "cd ..") {
            currentPosition = currentPosition.parent ?: filesystem
        } else if (call.startsWith("cd ")) {
            val target = call.substring(3)

            currentPosition = if (currentPosition.dirs.any { dir -> dir.name == target }) {
                currentPosition.dirs.first { dir -> dir.name == target }
            } else {
                currentPosition.subDir(target)
            }
        }
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}