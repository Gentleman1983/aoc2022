package de.havox_design.aoc2022.day07

import java.util.Objects

data class Directory(var parent: Directory?, var name: String, var dirs: Set<Directory>, var files: Set<File>) {
    fun calculateSizeOfFilesInDir(): Int {
        var size = 0

        for (file in files) {
            size += file.size
        }

        return size
    }

    fun calculateSizeOfFilesInDirAndSubDirs(): Int = calculateSizeOfFilesInDirAndSubDirs(Int.MAX_VALUE)

    fun calculateSizeOfFilesInDirAndSubDirs(limit: Int): Int {
        var size = 0

        size += calculateSizeOfFilesInDir()

        for (dir in dirs) {
            size += dir.calculateSizeOfFilesInDirAndSubDirs()
        }

        return if (size <= limit) {
            size
        } else {
            0
        }
    }

    fun findSmallestDirLargerThanLimit(limit: Int): Int {
        var size = calculateSizeOfFilesInDirAndSubDirs()
        if (size < limit) {
            return Int.MAX_VALUE
        }

        for (dir in dirs) {
            size = dir.findSmallestDirLargerThanLimit(limit).coerceAtMost(size)
        }

        return size
    }

    fun file(name: String, size: Int): File {
        val file = File(name, size)
        files += file

        return file
    }

    fun subDir(name: String): Directory = subDir(name, emptySet(), emptySet())

    private fun subDir(name: String, dirs: Set<Directory>, files: Set<File>): Directory {
        val subDir = Directory(this, name, dirs, files)
        this.dirs += subDir

        return subDir
    }

    override fun equals(other: Any?): Boolean =
        (other is Directory)
                && name == other.name
                && dirs == other.dirs
                && files == other.files
                && parent?.name == other.parent?.name

    override fun hashCode(): Int = Objects.hash(parent?.name, name, dirs, files)
    override fun toString(): String = "Directory[parent=${parent?.name}, name=$name, dirs=$dirs, files=$files]"
}
