package de.havox_design.aoc2022.day07

class NoSpaceLeftOnDevice(private var filename: String) {
    var filesystem: Directory = Directory(null, "/", emptySet(), emptySet())
    private var currentPosition: Directory = filesystem
    fun readData() {
        var fileData = getResourceAsText(filename)

        for (row in fileData) {
            if (row.startsWith("$ ")) {
                readSystemCall(row.substring(2))
            } else {
                readFileOrFolder(row)
            }
        }
    }

    private fun readFileOrFolder(call: String) {
        if(call.startsWith("dir ")) {
            val target = call.substring(4)

            if (currentPosition.dirs.none { dir -> dir.name == target }) {
                currentPosition.subDir(target)
            }
        }
        else {
            val elements = call.split(" ")
            val fileName = elements[1]
            val fileSize = elements[0].toInt()

            currentPosition.file(fileName, fileSize)
        }
    }

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
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader()!!.readLines()
}