package de.havox_design.aoc2022.day07

data class Directory(var parent: Directory?, var dirs: Set<Directory>, var files: Set<File>) {
    fun caculateSizeOfFilesInDir(): Int {
        var size = 0

        for(file in files) {
            size += file.size
        }

        return size
    }

    fun caculateSizeOfFilesInDirAndSubDirs(): Int {
        var size = 0;

        size += caculateSizeOfFilesInDir()

        for(dir in dirs) {
            size += dir.caculateSizeOfFilesInDir()
        }

        return size
    }
}
