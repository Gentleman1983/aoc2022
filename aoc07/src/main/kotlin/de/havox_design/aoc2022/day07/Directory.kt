package de.havox_design.aoc2022.day07

import java.util.Objects

data class Directory(var parent: Directory?, var name: String, var dirs: Set<Directory>, var files: Set<File>) {
    fun caculateSizeOfFilesInDir(): Int {
        var size = 0

        for (file in files) {
            size += file.size
        }

        return size
    }

    fun caculateSizeOfFilesInDirAndSubDirs(): Int {
        var size = 0;

        size += caculateSizeOfFilesInDir()

        for (dir in dirs) {
            size += dir.caculateSizeOfFilesInDir()
        }

        return size
    }

    fun file(name: String, size: Int): File {
        var file = File(name, size)
        files += file

        return file
    }

    fun subDir(name: String): Directory = subDir(name, emptySet<Directory>(), emptySet<File>())

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
