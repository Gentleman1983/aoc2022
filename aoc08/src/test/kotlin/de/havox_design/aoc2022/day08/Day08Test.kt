package de.havox_design.aoc2022.day08

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day08Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadSingleRowWoodByList")
    fun testReadSingleRowWoodByList(expectedTrees: List<Tree>) {
        val objectUnderTest = Wood()
        objectUnderTest.addRow(expectedTrees)

        assertAll(
            { objectUnderTest.getRows().shouldBe(1) },
            { objectUnderTest.getCols().shouldBe(expectedTrees.size) },
            {
                for (index in expectedTrees.indices) {
                    val expectedTree = expectedTrees[index]

                    objectUnderTest.getTree(0, index).shouldBe(expectedTree)
                }
            }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadSingleRowWood")
    fun testReadSingleRowWood(row: String, expectedTrees: List<Tree>) {
        val objectUnderTest = Wood()
        objectUnderTest.addRow(row)

        assertAll(
            { objectUnderTest.getRows().shouldBe(1) },
            { objectUnderTest.getCols().shouldBe(row.length) },
            {
                for (index in expectedTrees.indices) {
                    val expectedTree = expectedTrees[index]

                    objectUnderTest.getTree(0, index).shouldBe(expectedTree)
                }
            }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadWoodByFile")
    fun testReadWoodByFile(filename: String, expectedTrees: List<List<Tree>>) {
        val objectUnderTest = TreetopTreeHouse(filename)
        objectUnderTest.readFile()

        val wood = objectUnderTest.getWood()

        assertAll(
            { wood.getRows().shouldBe(expectedTrees.size) },
            { wood.getCols().shouldBe(expectedTrees[0].size) },
            {
                for (rowIndex in expectedTrees.indices) {
                    for (colIndex in expectedTrees[rowIndex].indices) {
                        val expectedTree = expectedTrees[rowIndex][colIndex]

                        wood.getTree(rowIndex, colIndex).shouldBe(expectedTree)
                    }
                }
            }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestTreeVisibility")
    fun testTreeVisibility(filename: String, row: Int, col: Int, expectedVisibility: Boolean) {
        val objectUnderTest = TreetopTreeHouse(filename)
        objectUnderTest.readFile()

        val wood = objectUnderTest.getWood()

        wood.isTreeVisible(row, col).shouldBe(expectedVisibility)
    }

    @ParameterizedTest
    @MethodSource("getDataForTestTreeVisibilityByDirection")
    fun testTreeVisibilityByDirection(
        filename: String,
        row: Int,
        col: Int,
        expectedVisibileFromNorth: Boolean,
        expectedVisibileFromEast: Boolean,
        expectedVisibileFromSouth: Boolean,
        expectedVisibileFromWest: Boolean
    ) {
        val objectUnderTest = TreetopTreeHouse(filename)
        objectUnderTest.readFile()

        val wood = objectUnderTest.getWood()

        assertAll(
            {
                wood.isTreeVisibleFrom(row, col, Direction.NORTH).shouldBe(
                    expectedVisibileFromNorth,
                    "Expected visibility from North to be $expectedVisibileFromNorth, but was ${!expectedVisibileFromNorth}"
                )
            },
            {
                wood.isTreeVisibleFrom(row, col, Direction.EAST).shouldBe(
                    expectedVisibileFromEast,
                    "Expected visibility from East to be $expectedVisibileFromEast, but was ${!expectedVisibileFromEast}"
                )
            },
            {
                wood.isTreeVisibleFrom(row, col, Direction.SOUTH).shouldBe(
                    expectedVisibileFromSouth,
                    "Expected visibility from South to be $expectedVisibileFromSouth, but was ${!expectedVisibileFromSouth}"
                )
            },
            {
                wood.isTreeVisibleFrom(row, col, Direction.WEST).shouldBe(
                    expectedVisibileFromWest,
                    "Expected visibility from West to be $expectedVisibileFromWest, but was ${!expectedVisibileFromWest}"
                )
            }
        )

    }

    @ParameterizedTest
    @CsvSource("sample.txt,21")
    fun testProcessPart1(filename: String, expectedNumberOfTrees: Int) =
        TreetopTreeHouse(filename).processPart1().shouldBe(expectedNumberOfTrees)

    companion object {
        @JvmStatic
        private fun getDataForTestReadSingleRowWoodByList(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(Tree(1))),
                Arguments.of(listOf(Tree(1), Tree(2), Tree(3), Tree(4), Tree(5))),
                Arguments.of(listOf(Tree(0), Tree(5), Tree(3), Tree(4), Tree(2), Tree(8), Tree(2)))
            )

        @JvmStatic
        private fun getDataForTestReadSingleRowWood(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "1",
                    listOf(Tree(1))
                ),
                Arguments.of(
                    "12345",
                    listOf(Tree(1), Tree(2), Tree(3), Tree(4), Tree(5))
                ),
                Arguments.of(
                    "0534282",
                    listOf(Tree(0), Tree(5), Tree(3), Tree(4), Tree(2), Tree(8), Tree(2))
                )
            )

        @JvmStatic
        private fun getDataForTestReadWoodByFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        listOf(Tree(3), Tree(0), Tree(3), Tree(7), Tree(3)),
                        listOf(Tree(2), Tree(5), Tree(5), Tree(1), Tree(2)),
                        listOf(Tree(6), Tree(5), Tree(3), Tree(3), Tree(2)),
                        listOf(Tree(3), Tree(3), Tree(5), Tree(4), Tree(9)),
                        listOf(Tree(3), Tree(5), Tree(3), Tree(9), Tree(0))
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestTreeVisibility(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1, 1, true),
                Arguments.of("sample.txt", 1, 2, true),
                Arguments.of("sample.txt", 1, 3, false),
                Arguments.of("sample.txt", 2, 1, true),
                Arguments.of("sample.txt", 2, 2, false),
                Arguments.of("sample.txt", 2, 3, true),
                Arguments.of("sample.txt", 3, 1, false),
                Arguments.of("sample.txt", 3, 2, true),
                Arguments.of("sample.txt", 3, 3, false)
            )

        @JvmStatic
        private fun getDataForTestTreeVisibilityByDirection(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1, 1, true, false, false, true),
                Arguments.of("sample.txt", 1, 2, true, true, false, false),
                Arguments.of("sample.txt", 1, 3, false, false, false, false),
                Arguments.of("sample.txt", 2, 1, false, true, false, false),
                Arguments.of("sample.txt", 2, 2, false, false, false, false),
                Arguments.of("sample.txt", 2, 3, false, true, false, false),
                Arguments.of("sample.txt", 3, 1, false, false, false, false),
                Arguments.of("sample.txt", 3, 2, false, false, true, true),
                Arguments.of("sample.txt", 3, 3, false, false, false, false)
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Boolean.shouldBe(expectation: Boolean, message: String) =
    Assertions.assertEquals(expectation, this, message)

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Tree.shouldBe(expectation: Tree) = Assertions.assertEquals(expectation, this)
