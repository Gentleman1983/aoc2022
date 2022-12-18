package de.havox_design.aoc2022.day17

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day17Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("training"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Long) =
        PyroclasticFlow(filename).processPart1().shouldBe(expectedResult)

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        PyroclasticFlow(filename).processPart1(1000000000000).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestRocks")
    fun testRocks(rock: Rock, expectedWidth: Long, expectedHeigth: Long, expectedBlockedElements: Set<Position>) =
        assertAll(
            { rock.dimensionX.shouldBe(expectedWidth) },
            { rock.dimensionY.shouldBe(expectedHeigth) },
            { rock.getBlockedPositions().shouldBe(expectedBlockedElements) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestJets")
    fun testJets(code: String, expectedJet: Jet) =
        Jet.getJetForCode(code).shouldBe(expectedJet)

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, expectedJets: List<Jet>) =
        PyroclasticFlow(filename).jetPattern.shouldBe(expectedJets)

    @ParameterizedTest
    @MethodSource("getDataForTestRockSpawning")
    fun testRockSpawning(expectedPosition: Position) =
        Chamber().getStartPositionForRock().shouldBe(expectedPosition)

    @ParameterizedTest
    @MethodSource("getDataForTestStatusAfterStone")
    fun testStatusAfterStone(filename: String, stones: Long, expectedBlockers: Set<Position>) {
        val objectUnderTest = PyroclasticFlow(filename)
        objectUnderTest.processPart1(stones)

        objectUnderTest.chamber.obstacles.shouldContainAll(expectedBlockers)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 3068)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1514285714288)
            )

        @JvmStatic
        private fun getDataForTestRocks(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Rock.ARROW, 3, 3, getBlockedFieldsForString("..#\n..#\n###")),
                Arguments.of(Rock.BOX, 2, 2, getBlockedFieldsForString("##\n##")),
                Arguments.of(Rock.HORIZONTAL_LINE, 4, 1, getBlockedFieldsForString("####")),
                Arguments.of(Rock.PLUS, 3, 3, getBlockedFieldsForString(".#.\n###\n.#.")),
                Arguments.of(Rock.VERTICAL_LINE, 1, 4, getBlockedFieldsForString("#\n#\n#\n#"))
            )

        @JvmStatic
        private fun getDataForTestJets(): Stream<Arguments> =
            Stream.of(
                Arguments.of("<", Jet.LEFT),
                Arguments.of(">", Jet.RIGHT),
                Arguments.of("v", Jet.DOWN),
                Arguments.of("foo", Jet.UNKNOWN),
                Arguments.of("bar", Jet.UNKNOWN),
                Arguments.of(".", Jet.UNKNOWN)
            )

        @JvmStatic
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", toJetList(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"))
            )

        @JvmStatic
        private fun getDataForTestRockSpawning(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Position(2, 3))
            )

        @JvmStatic
        private fun getDataForTestStatusAfterStone(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    1,
                    getBlockedFieldsForString("..####.")
                ),
                Arguments.of(
                    "sample.txt",
                    2,
                    getBlockedFieldsForString(
                        "...#...\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    3,
                    getBlockedFieldsForString(
                        "..#....\n" +
                                "..#....\n" +
                                "####...\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    4,
                    getBlockedFieldsForString(
                        "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    5,
                    getBlockedFieldsForString(
                        "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    6,
                    getBlockedFieldsForString(
                        ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    7,
                    getBlockedFieldsForString(
                        "..#....\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    8,
                    getBlockedFieldsForString(
                        ".....#.\n" +
                                ".....#.\n" +
                                "..####.\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    9,
                    getBlockedFieldsForString(
                        "....#..\n" +
                                "....#..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "..####.\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "sample.txt",
                    10,
                    getBlockedFieldsForString(
                        "....#..\n" +
                                "....#..\n" +
                                "....##.\n" +
                                "##..##.\n" +
                                "######.\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                )
            )

        private fun toJetList(data: String): List<Jet> {
            val jets = emptyList<Jet>().toMutableList()

            for (index in data.indices) {
                jets += Jet.getJetForCode(data.substring(index, index + 1))
            }

            return jets
        }

        private fun getBlockedFieldsForString(data: String): Set<Position> {
            val result: MutableSet<Position> = emptySet<Position>().toMutableSet()

            val rows = data.split("\n")

            for (rowIndex in rows.indices) {
                val row = rows[rowIndex]

                for (colIndex in row.indices) {
                    val letter = row[colIndex]

                    if (letter == '#') {
                        result += Position(colIndex.toLong(), (rows.size - rowIndex - 1).toLong())
                    }
                }
            }

            return result
        }
    }
}

private fun Jet.shouldBe(expectation: Jet) = Assertions.assertEquals(expectation, this)
private fun List<*>.shouldBe(expectation: List<*>) = Assertions.assertEquals(expectation, this)
private fun Long.shouldBe(expectation: Long) = Assertions.assertEquals(expectation, this)
private fun Position.shouldBe(expectation: Position) = Assertions.assertEquals(expectation, this)
private fun Set<*>.shouldBe(expectation: Set<*>) = Assertions.assertEquals(expectation, this)
private fun Set<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))
