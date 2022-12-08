package de.havox_design.aoc2022.day08

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
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
            {objectUnderTest.getRows().shouldBe(1)},
            {objectUnderTest.getCols().shouldBe(expectedTrees.size)},
            {
                for(index in expectedTrees.indices) {
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
            {objectUnderTest.getRows().shouldBe(1)},
            {objectUnderTest.getCols().shouldBe(row.length)},
            {
                for(index in expectedTrees.indices) {
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
            {wood.getRows().shouldBe(expectedTrees.size)},
            {wood.getCols().shouldBe(expectedTrees[0].size)},
            {
                for(rowIndex in expectedTrees.indices) {
                    for(colIndex in expectedTrees[rowIndex].indices) {
                        val expectedTree = expectedTrees[rowIndex][colIndex]

                        wood.getTree(rowIndex, colIndex).shouldBe(expectedTree)
                    }
                }
            }
        )
    }

    companion object{
        @JvmStatic
        private fun getDataForTestReadSingleRowWoodByList(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(Tree(1))),
                Arguments.of(listOf(Tree(1),Tree(2),Tree(3),Tree(4),Tree(5))),
                Arguments.of(listOf(Tree(0),Tree(5),Tree(3),Tree(4),Tree(2),Tree(8),Tree(2)))
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
                    listOf(Tree(1),Tree(2),Tree(3),Tree(4),Tree(5))
                ),
                Arguments.of(
                    "0534282",
                    listOf(Tree(0),Tree(5),Tree(3),Tree(4),Tree(2),Tree(8),Tree(2))
                )
            )

        @JvmStatic
        private fun getDataForTestReadWoodByFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        listOf(Tree(3),Tree(0),Tree(3),Tree(7),Tree(3)),
                        listOf(Tree(2),Tree(5),Tree(5),Tree(1),Tree(2)),
                        listOf(Tree(6),Tree(5),Tree(3),Tree(3),Tree(2)),
                        listOf(Tree(3),Tree(3),Tree(5),Tree(4),Tree(9)),
                        listOf(Tree(3),Tree(5),Tree(3),Tree(9),Tree(0))
                    )
                )
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Tree.shouldBe(expectation: Tree) = Assertions.assertEquals(expectation, this)
