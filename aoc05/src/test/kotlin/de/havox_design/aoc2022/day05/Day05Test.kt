package de.havox_design.aoc2022.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertNotNull

class Day05Test {
    @Test
    fun testStack() {
        val expectedId = 42
        val expectedCrate1 = Crate("DEEP THOUGHT")
        val expectedCrate2 = Crate("HEART OF GOLD")

        val objectUnderTest = Stack.emptyStackWithId(expectedId)
        objectUnderTest.stack += expectedCrate1
        objectUnderTest.stack += expectedCrate2

        assertAll(
            { assertNotNull(objectUnderTest.id) },
            { objectUnderTest.id.shouldBe(expectedId) },
            { objectUnderTest.stack.shouldContainAll(listOf(expectedCrate1, expectedCrate2)) }
        )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun List<Crate>.shouldContainAll(expectation: Collection<Crate>) =
    Assertions.assertTrue(this.containsAll(expectation))