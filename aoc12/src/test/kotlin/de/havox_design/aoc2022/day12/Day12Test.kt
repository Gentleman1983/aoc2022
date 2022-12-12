package de.havox_design.aoc2022.day12

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day12Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestFieldReachable")
    fun testFieldReachable(source: Field, target: Field, expectedToBeVisited: Boolean) =
        source.canVisitField(target).shouldBe(expectedToBeVisited)

    @ParameterizedTest
    @MethodSource("getDataForTestGetElevationBySymbol")
    fun testGetElevationBySymbol(one: String, other: String) =
        HeightMapping.getElevationBySymbol(one).shouldBe(HeightMapping.getElevationBySymbol(other))

    @ParameterizedTest
    @MethodSource("getDataForTestInitLandscape")
    fun testInitLandscape(data: List<String>, expectedMapData: Map<Position, Field>) =
        Landscape(data).map.shouldBe(expectedMapData)

    @ParameterizedTest
    @MethodSource("getDataForTestReadFile")
    fun testGetReadFile(filename: String, expectedMapData: Map<Position, Field>) =
        HillClimbingAlgorithm(filename).landscape.map.shouldBe(expectedMapData)

    @ParameterizedTest
    @MethodSource("getDataForTestPart1")
    fun testPart1(filename: String, expectedSteps: Int) =
        HillClimbingAlgorithm(filename).processPart1().shouldBe(expectedSteps)

    @ParameterizedTest
    @MethodSource("getDataForTestPart2")
    fun testPart2(filename: String, expectedSteps: Int) =
        HillClimbingAlgorithm(filename).processPart2().shouldBe(expectedSteps)


    companion object {
        @JvmStatic
        private fun getDataForTestFieldReachable(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Field("a"), Field("b"), true),
                Arguments.of(Field("S"), Field("a"), true),
                Arguments.of(Field("S"), Field("b"), true),
                Arguments.of(Field("S"), Field("c"), false),
                Arguments.of(Field("x"), Field("E"), false),
                Arguments.of(Field("y"), Field("E"), true),
                Arguments.of(Field("z"), Field("E"), true),
                Arguments.of(Field("o"), Field("o"), true),
                Arguments.of(Field("E"), Field("S"), true)
            )

        @JvmStatic
        private fun getDataForTestGetElevationBySymbol(): Stream<Arguments> =
            Stream.of(
                Arguments.of("a", "a"),
                Arguments.of("S", "a"),
                Arguments.of("a", "S"),
                Arguments.of("z", "z"),
                Arguments.of("z", "E"),
                Arguments.of("E", "z")
            )

        @JvmStatic
        private fun getDataForTestInitLandscape(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    listOf(
                        "Sabqponm",
                        "abcryxxl",
                        "accszExk",
                        "acctuvwj",
                        "abdefghi"
                    ),
                    mapOf(
                        Pair(Position(0, 0), Field("S")),
                        Pair(Position(1, 0), Field("a")),
                        Pair(Position(2, 0),Field( "b")),
                        Pair(Position(3, 0), Field("q")),
                        Pair(Position(4, 0), Field("p")),
                        Pair(Position(5, 0), Field("o")),
                        Pair(Position(6, 0), Field("n")),
                        Pair(Position(7, 0), Field("m")),
                        Pair(Position(0, 1), Field("a")),
                        Pair(Position(1, 1), Field("b")),
                        Pair(Position(2, 1), Field("c")),
                        Pair(Position(3, 1), Field("r")),
                        Pair(Position(4, 1), Field("y")),
                        Pair(Position(5, 1), Field("x")),
                        Pair(Position(6, 1), Field("x")),
                        Pair(Position(7, 1), Field("l")),
                        Pair(Position(0, 2), Field("a")),
                        Pair(Position(1, 2), Field("c")),
                        Pair(Position(2, 2), Field("c")),
                        Pair(Position(3, 2), Field("s")),
                        Pair(Position(4, 2), Field("z")),
                        Pair(Position(5, 2), Field("E")),
                        Pair(Position(6, 2), Field("x")),
                        Pair(Position(7, 2), Field("k")),
                        Pair(Position(0, 3), Field("a")),
                        Pair(Position(1, 3), Field("c")),
                        Pair(Position(2, 3), Field("c")),
                        Pair(Position(3, 3), Field("t")),
                        Pair(Position(4, 3), Field("u")),
                        Pair(Position(5, 3), Field("v")),
                        Pair(Position(6, 3), Field("w")),
                        Pair(Position(7, 3), Field("j")),
                        Pair(Position(0, 4), Field("a")),
                        Pair(Position(1, 4), Field("b")),
                        Pair(Position(2, 4), Field("d")),
                        Pair(Position(3, 4), Field("e")),
                        Pair(Position(4, 4), Field("f")),
                        Pair(Position(5, 4), Field("g")),
                        Pair(Position(6, 4), Field("h")),
                        Pair(Position(7, 4), Field("i"))
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    mapOf(
                        Pair(Position(0, 0), Field("S")),
                        Pair(Position(1, 0), Field("a")),
                        Pair(Position(2, 0),Field( "b")),
                        Pair(Position(3, 0), Field("q")),
                        Pair(Position(4, 0), Field("p")),
                        Pair(Position(5, 0), Field("o")),
                        Pair(Position(6, 0), Field("n")),
                        Pair(Position(7, 0), Field("m")),
                        Pair(Position(0, 1), Field("a")),
                        Pair(Position(1, 1), Field("b")),
                        Pair(Position(2, 1), Field("c")),
                        Pair(Position(3, 1), Field("r")),
                        Pair(Position(4, 1), Field("y")),
                        Pair(Position(5, 1), Field("x")),
                        Pair(Position(6, 1), Field("x")),
                        Pair(Position(7, 1), Field("l")),
                        Pair(Position(0, 2), Field("a")),
                        Pair(Position(1, 2), Field("c")),
                        Pair(Position(2, 2), Field("c")),
                        Pair(Position(3, 2), Field("s")),
                        Pair(Position(4, 2), Field("z")),
                        Pair(Position(5, 2), Field("E")),
                        Pair(Position(6, 2), Field("x")),
                        Pair(Position(7, 2), Field("k")),
                        Pair(Position(0, 3), Field("a")),
                        Pair(Position(1, 3), Field("c")),
                        Pair(Position(2, 3), Field("c")),
                        Pair(Position(3, 3), Field("t")),
                        Pair(Position(4, 3), Field("u")),
                        Pair(Position(5, 3), Field("v")),
                        Pair(Position(6, 3), Field("w")),
                        Pair(Position(7, 3), Field("j")),
                        Pair(Position(0, 4), Field("a")),
                        Pair(Position(1, 4), Field("b")),
                        Pair(Position(2, 4), Field("d")),
                        Pair(Position(3, 4), Field("e")),
                        Pair(Position(4, 4), Field("f")),
                        Pair(Position(5, 4), Field("g")),
                        Pair(Position(6, 4), Field("h")),
                        Pair(Position(7, 4), Field("i"))
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 31)
            )

        @JvmStatic
        private fun getDataForTestPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 29)
            )
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Map<*, *>?.shouldBe(expectation: Map<*, *>?) = Assertions.assertEquals(expectation, this)
