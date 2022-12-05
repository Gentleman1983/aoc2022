package de.havox_design.aoc2022.day05

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertNotNull

class Day05Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

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
                    val expectedStack = expectedData[index]!!.stack
                    objectUnderTest.data[index]!!.stack.shouldContainAll(expectedStack)
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    val objectUnderTestStack = objectUnderTest.data[index]!!.stack

                    for (i in expectedStack.indices) {
                        objectUnderTestStack[i].shouldBe(expectedStack[i])
                    }
                }
            }
        )
    }

    @Test
    fun testReadProcedures() {
        val objectUnderTest = SupplyStacks("sample.txt")

        objectUnderTest.readData()
        val expectedData = getDataForTestReadProcedures(objectUnderTest.data)

        assertAll(
            { assertNotNull(objectUnderTest.procedure) },
            // All procedure steps in correct order
            {
                for (index in expectedData.indices) {
                    objectUnderTest.procedure[index].shouldBe(expectedData[index])
                }
            }
        )
    }

    @Test
    fun testRunProcedure() {
        val expectedData = getDataForTestRunProcedure()
        val objectUnderTest = SupplyStacks("sample.txt")

        objectUnderTest.readData()
        objectUnderTest.followProcedure()

        assertAll(
            { assertNotNull(objectUnderTest.data) },
            { assertNotNull(objectUnderTest.procedure) },
            // All procedure steps in correct order
            // Contains all stacks
            { objectUnderTest.data.keys.shouldContainAll(expectedData.keys) },
            // Contains all elements
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    objectUnderTest.data[index]!!.stack.shouldContainAll(expectedStack)
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    val objectUnderTestStack = objectUnderTest.data[index]!!.stack

                    for (i in expectedStack.indices) {
                        objectUnderTestStack[i].shouldBe(expectedStack[i])
                    }
                }
            }
        )
    }

    @Test
    fun testRunProcedureCratemaster9001() {
        val expectedData = getDataForTestRunProcedureCratemaster9001()
        val objectUnderTest = SupplyStacks("sample.txt")

        objectUnderTest.readData()
        objectUnderTest.followProcedureCratemaster9001()

        assertAll(
            { assertNotNull(objectUnderTest.data) },
            { assertNotNull(objectUnderTest.procedure) },
            // All procedure steps in correct order
            // Contains all stacks
            { objectUnderTest.data.keys.shouldContainAll(expectedData.keys) },
            // Contains all elements
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    objectUnderTest.data[index]!!.stack.shouldContainAll(expectedStack)
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    val objectUnderTestStack = objectUnderTest.data[index]!!.stack

                    for (i in expectedStack.indices) {
                        objectUnderTestStack[i].shouldBe(expectedStack[i])
                    }
                }
            }
        )
    }

    @Test
    fun testSolutionPart1() =
        SupplyStacks("sample.txt").evaluateTask1().shouldBe("CMZ")

    @Test
    fun testSolutionPart2() =
        SupplyStacks("sample.txt").evaluateTask2().shouldBe("MCD")

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

        val testData = emptyMap<Int, Stack>().toMutableMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }

    private fun getDataForTestReadProcedures(data: Map<Int, Stack>): List<Step> {
        val proc = emptyList<Step>().toMutableList()

        proc += Step(1, data[2]!!, data[1]!!)
        proc += Step(3, data[1]!!, data[3]!!)
        proc += Step(2, data[2]!!, data[1]!!)
        proc += Step(1, data[1]!!, data[2]!!)

        return proc
    }

    private fun getDataForTestRunProcedure(): Map<Int, Stack> {
        val stack1 = Stack.emptyStackWithId(1)
        stack1.stack += Crate("C")

        val stack2 = Stack.emptyStackWithId(2)
        stack2.stack += Crate("M")

        val stack3 = Stack.emptyStackWithId(3)
        stack3.stack += Crate("P")
        stack3.stack += Crate("D")
        stack3.stack += Crate("N")
        stack3.stack += Crate("Z")

        val testData = emptyMap<Int, Stack>().toMutableMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }

    private fun getDataForTestRunProcedureCratemaster9001(): Map<Int, Stack> {
        val stack1 = Stack.emptyStackWithId(1)
        stack1.stack += Crate("M")

        val stack2 = Stack.emptyStackWithId(2)
        stack2.stack += Crate("C")

        val stack3 = Stack.emptyStackWithId(3)
        stack3.stack += Crate("P")
        stack3.stack += Crate("Z")
        stack3.stack += Crate("N")
        stack3.stack += Crate("D")

        val testData = emptyMap<Int, Stack>().toMutableMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }
}

private fun Crate.shouldBe(expectation: Crate) = Assertions.assertEquals(expectation, this)
private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun Step.shouldBe(expectation: Step) = Assertions.assertEquals(expectation, this)
private fun String.shouldBe(expectation: String) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))
