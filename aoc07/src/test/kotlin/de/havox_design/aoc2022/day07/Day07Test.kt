package de.havox_design.aoc2022.day07

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day07Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestDirectoryCalculateSizeOfFilesInDir")
    fun testDirectoryCalculateSizeOfFilesInDir(fileSizes: List<Int>, expectedSize: Int) {
        val dir = Directory(null, "/", emptySet(), emptySet())

        for (size in fileSizes) {
            dir.files += File("file$size", size)
        }

        dir.calculateSizeOfFilesInDir().shouldBe(expectedSize)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestDirectoryCalculateSizeOfFilesInDirAndSubdirs")
    fun testDirectoryCalculateSizeOfFilesInDirAndSubdirs(
        fileSizes: List<Int>,
        subDirFileSizes: List<Int>,
        expectedSize: Int
    ) {
        val dir = Directory(null, "/", emptySet(), emptySet())
        val subDir = Directory(dir, "subDir", emptySet(), emptySet())

        dir.dirs += subDir

        for (size in subDirFileSizes) {
            subDir.files += File("file$size", size)
        }

        for (size in fileSizes) {
            dir.files += File("file$size", size)
        }

        dir.calculateSizeOfFilesInDirAndSubDirs().shouldBe(expectedSize)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadFolder")
    fun testReadFolder(filename: String, expectedFileSystem: Directory) {
        val objectUnderTest = NoSpaceLeftOnDevice(filename)
        objectUnderTest.readData()

        objectUnderTest.getFs().toString().shouldBe(expectedFileSystem.toString())
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt,48381165"
    )
    fun testSumAllFiles(filename: String, expectedSize: Int) {
        val data = NoSpaceLeftOnDevice(filename)
        data.readData()

        data.getFs().calculateSizeOfFilesInDirAndSubDirs().shouldBe(expectedSize)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt,24933642"
    )
    fun testFindSmallestDirLargerThanLimit(filename: String, expectedSize: Int) {
        val missingSpace = 8381165
        val data = NoSpaceLeftOnDevice(filename)
        data.readData()

        data.getFs().findSmallestDirLargerThanLimit(missingSpace).shouldBe(expectedSize)
    }

    @ParameterizedTest
    @CsvSource(
        "sample.txt,95437"
    )
    fun testProcessPart1(filename: String, expectedSize: Int) =
        NoSpaceLeftOnDevice(filename).processPart1().shouldBe(expectedSize)

    @ParameterizedTest
    @CsvSource(
        "sample.txt,24933642"
    )
    fun testProcessPart2(filename: String, expectedSize: Int) =
        NoSpaceLeftOnDevice(filename).processPart2().shouldBe(expectedSize)

    companion object {
        @JvmStatic
        private fun getDataForTestDirectoryCalculateSizeOfFilesInDir(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(1), 1),
                Arguments.of(listOf(1, 2), 3),
                Arguments.of(listOf(1, 2, 3, 5, 7), 18),
                Arguments.of(listOf(13, 23, 42), 78),
                Arguments.of(listOf(53, 666), 719),
                Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 45)
            )

        @JvmStatic
        private fun getDataForTestDirectoryCalculateSizeOfFilesInDirAndSubdirs(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(99, 98, 97, 6), listOf(1), 301),
                Arguments.of(listOf(99, 98, 97, 6), listOf(1, 2), 303),
                Arguments.of(listOf(99, 98, 97, 6), listOf(1, 2, 3, 5, 7), 318),
                Arguments.of(listOf(99, 98, 97, 6), listOf(13, 23, 42), 378),
                Arguments.of(listOf(99, 98, 97, 6), listOf(53, 666), 1019),
                Arguments.of(listOf(99, 98, 97, 6), listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), 345)
            )

        @JvmStatic
        private fun getDataForTestReadFolder(): Stream<Arguments> {
            val dirE = Directory(null, "e", emptySet(), setOf(File("i", 584)))
            val dirA = Directory(
                null,
                "a",
                setOf(dirE),
                setOf(
                    File("f", 29116),
                    File("g", 2557),
                    File("h.lst", 62596)
                )
            )
            dirE.parent = dirA
            val dirD = Directory(
                null,
                "d",
                emptySet(),
                setOf(
                    File("j", 4060174),
                    File("d.log", 8033020),
                    File("d.ext", 5626152),
                    File("k", 7214296)
                )
            )
            val rootDir = Directory(
                null,
                "/",
                setOf(dirA, dirD),
                setOf(
                    File("b.txt", 14848514),
                    File("c.dat", 8504156)
                )
            )
            dirA.parent = rootDir
            dirD.parent = rootDir

            return Stream.of(Arguments.of("sample.txt", rootDir))
        }
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun String.shouldBe(expectation: String) = Assertions.assertEquals(expectation, this)
