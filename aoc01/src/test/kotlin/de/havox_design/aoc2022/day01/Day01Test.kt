package de.havox_design.aoc2022.day01

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day01Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @Test
    fun singleEntry() =
        CaloriesCounter("singleEntry.txt")
            .processFile()
            .shouldBe("42")


    @Test
    fun singleSum() =
        CaloriesCounter("singleSum.txt")
            .processFile()
            .shouldBe("797")

    @Test
    fun sample() =
        CaloriesCounter("sample.txt")
            .processFile()
            .shouldBe("24000")

    @Test
    fun sampleTopThree() =
        CaloriesCounter("sample.txt")
            .processFileTopThree()
            .shouldBe("45000")
}

private fun String.shouldBe(expectation: String) = assertEquals(expectation, this)
