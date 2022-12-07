package de.havox_design.aoc2022.day07

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day07Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestDirectoryCaculateSizeOfFilesInDir")
    fun testDirectoryCaculateSizeOfFilesInDir(fileSizes: List<Int>, expectedSize: Int) {
        var dir = Directory(null, emptySet<Directory>(), emptySet<File>())

        for(size in fileSizes) {
            dir.files += File("file$size", size)
        }

        dir.caculateSizeOfFilesInDir().shouldBe(expectedSize)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestDirectoryCaculateSizeOfFilesInDirAndSubdirs")
    fun testDirectoryCaculateSizeOfFilesInDirAndSubdirs(fileSizes: List<Int>, subDirFileSizes: List<Int>, expectedSize: Int) {
        var dir = Directory(null, emptySet<Directory>(), emptySet<File>())
        var subDir = Directory(dir, emptySet<Directory>(), emptySet<File>())

        dir.dirs += subDir

        for(size in subDirFileSizes) {
            subDir.files += File("file$size", size)
        }

        for(size in fileSizes) {
            dir.files += File("file$size", size)
        }

        dir.caculateSizeOfFilesInDirAndSubDirs().shouldBe(expectedSize)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestDirectoryCaculateSizeOfFilesInDir(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1), 1),
                Arguments.of(listOf(1,2), 3),
                Arguments.of(listOf(1,2,3,5,7), 18),
                Arguments.of(listOf(13,23,42),78),
                Arguments.of(listOf(53,666), 719),
                Arguments.of(listOf(1,2,3,4,5,6,7,8,9),45)
            )

        @JvmStatic
        private fun getDataForTestDirectoryCaculateSizeOfFilesInDirAndSubdirs(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(99,98,97,6),listOf(1), 301),
                Arguments.of(listOf(99,98,97,6),listOf(1,2), 303),
                Arguments.of(listOf(99,98,97,6),listOf(1,2,3,5,7), 318),
                Arguments.of(listOf(99,98,97,6),listOf(13,23,42),378),
                Arguments.of(listOf(99,98,97,6),listOf(53,666), 1019),
                Arguments.of(listOf(99,98,97,6),listOf(1,2,3,4,5,6,7,8,9),345)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)