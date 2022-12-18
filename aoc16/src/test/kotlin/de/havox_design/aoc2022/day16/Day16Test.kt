package de.havox_design.aoc2022.day16

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day16Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf("testing"))
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        ProboscideaVolcanium(filename).processPart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        ProboscideaVolcanium(filename).processPart2().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestCreateValves")
    fun testCreateValves(creationString: String, expectedValve: Valve) =
        Valve.from(creationString).shouldBe(expectedValve)

    @ParameterizedTest
    @MethodSource("getDataForTestImportValves")
    fun testImportValves(filename: String, expectedValves: Map<String, Valve>) =
        ProboscideaVolcanium(filename).valves.shouldBe(expectedValves)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1651)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sample.txt", 1707)
            )

        @JvmStatic
        private fun getDataForTestCreateValves(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "Valve AA has flow rate=0; tunnels lead to valves DD, II, BB",
                    Valve("AA", 0, listOf("DD", "II", "BB"))
                ),
                Arguments.of(
                    "Valve BB has flow rate=13; tunnels lead to valves CC, AA",
                    Valve("BB", 13, listOf("CC", "AA"))
                ),
                Arguments.of(
                    "Valve CC has flow rate=2; tunnels lead to valves DD, BB",
                    Valve("CC", 2, listOf("DD", "BB"))
                ),
                Arguments.of(
                    "Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE",
                    Valve("DD", 20, listOf("CC", "AA", "EE"))
                ),
                Arguments.of(
                    "Valve EE has flow rate=3; tunnels lead to valves FF, DD",
                    Valve("EE", 3, listOf("FF", "DD"))
                ),
                Arguments.of(
                    "Valve FF has flow rate=0; tunnels lead to valves EE, GG",
                    Valve("FF", 0, listOf("EE", "GG"))
                ),
                Arguments.of(
                    "Valve GG has flow rate=0; tunnels lead to valves FF, HH",
                    Valve("GG", 0, listOf("FF", "HH"))
                ),
                Arguments.of(
                    "Valve HH has flow rate=22; tunnel leads to valve GG",
                    Valve("HH", 22, listOf("GG"))
                ),
                Arguments.of(
                    "Valve II has flow rate=0; tunnels lead to valves AA, JJ",
                    Valve("II", 0, listOf("AA", "JJ"))
                ),
                Arguments.of(
                    "Valve JJ has flow rate=21; tunnel leads to valve II",
                    Valve("JJ", 21, listOf("II"))
                )

            )

        @JvmStatic
        private fun getDataForTestImportValves(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    mapOf(
                        Pair("AA", Valve("AA", 0, listOf("DD", "II", "BB"))),
                        Pair("BB", Valve("BB", 13, listOf("CC", "AA"))),
                        Pair("CC", Valve("CC", 2, listOf("DD", "BB"))),
                        Pair("DD", Valve("DD", 20, listOf("CC", "AA", "EE"))),
                        Pair("EE", Valve("EE", 3, listOf("FF", "DD"))),
                        Pair("FF", Valve("FF", 0, listOf("EE", "GG"))),
                        Pair("GG", Valve("GG", 0, listOf("FF", "HH"))),
                        Pair("HH", Valve("HH", 22, listOf("GG"))),
                        Pair("II", Valve("II", 0, listOf("AA", "JJ"))),
                        Pair("JJ", Valve("JJ", 21, listOf("II")))
                    )
                )
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Map<*, *>.shouldBe(expectation: Map<*, *>) = Assertions.assertEquals(expectation, this)
private fun Valve.shouldBe(expectation: Valve) = Assertions.assertEquals(expectation, this)
