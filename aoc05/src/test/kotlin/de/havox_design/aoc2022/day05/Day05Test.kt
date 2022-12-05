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

    @Test
    fun testReadStacks() {
        val expectedData = getDataForTestReadStacks()
        val objectUnderTest = SupplyStacks("sample.txt")

        objectUnderTest.readData()

        assertAll(
            { assertNotNull(objectUnderTest.data) },
            // Contains all stacks
            { objectUnderTest.data.keys.shouldContainAll(expectedData.keys) },
            // Contains all elements
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]?.stack
                    if (!expectedStack.isNullOrEmpty()) {
                        objectUnderTest.data[index]?.stack?.shouldContainAll(expectedStack)
                    }
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]?.stack
                    val objectUnderTestStack = objectUnderTest.data[index]?.stack

                    if(!expectedStack.isNullOrEmpty()) {
                        for (i in expectedStack.indices) {
                            objectUnderTestStack?.get(i)?.shouldBe(expectedStack[i])
                        }
                    }
                }
            }
        )
    }

    private fun getDataForTestReadStacks(): Map<Int, Stack> {
        val stack1 = Stack.emptyStackWithId(1)
        stack1.stack += Crate("Z")
        stack1.stack += Crate("N")

        val stack2 = Stack.emptyStackWithId(2)
        stack2.stack += Crate("M")
        stack2.stack += Crate("C")
        stack2.stack += Crate("D")

        val stack3 = Stack.emptyStackWithId(3)
        stack3.stack += Crate("P")

        var testData: Map<Int, Stack> = emptyMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }
}

private fun Crate.shouldBe(expectation: Crate) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))