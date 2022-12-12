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
        HeightMapping.getElevationBySymbol(one).shouldBe(HeightMapping.getElevationBySymbol(other))@ParameterizedTest

    @MethodSource("getDataForTestReadFile")
    fun testGetReadFile(filename: String, expectedMapData: List<List<Field>>) =
        HillClimbingAlgorithm(filename).landscape.map.shouldBe(expectedMapData)

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
        private fun getDataForTestReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample.txt",
                    listOf(
                        listOfFields("S","a","b","q","p","o","n","m"),
                        listOfFields("a","b","c","r","y","x","x","l"),
                        listOfFields("a","c","c","s","z","E","x","k"),
                        listOfFields("a","c","c","t","u","v","w","j"),
                        listOfFields("a","b","d","e","f","g","h","i")
                    )
                )
            )

        private fun listOfFields(vararg fields: String): List<Field> {
            val list = emptyList<Field>().toMutableList()

            for (field in fields) {
                list += Field(field)
            }

            return list
        }
    }
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Collection<*>?.shouldBe(expectation: Collection<*>?) = Assertions.assertEquals(expectation, this)
