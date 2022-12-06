package de.havox_design.aoc2022.day06

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day06Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @CsvSource(
        "abc,false",
        "abcd,true",
        "abcde,false"
    )
    fun testHasDetectionWindowCorrectLength(data: String, expectedLengthCorrect: Boolean) =
        DetectionWindow(data).hasCorrectLength().shouldBe(expectedLengthCorrect)

    @ParameterizedTest
    @CsvSource(
        "abc,false",
        "aacd,false",
        "bbcd,false",
        "abcd,true",
        "abdd,false",
        "abcc,false",
        "xxxx,false",
        "abcde,false"
    )
    fun testIsDetectionWindowPacketMarker(data: String, expectedLengthCorrect: Boolean) =
        DetectionWindow(data).isPacketMarker().shouldBe(expectedLengthCorrect)
}

private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)
