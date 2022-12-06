package de.havox_design.aoc2022.day04

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day04Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestAssignment")
    fun testAssignment(
        sectionString: String,
        expectedLowerBond: Int,
        expectedUpperBond: Int,
        expectedSections: List<Int>
    ) {
        val objectUnderTest: Assignment = Assignment.processSectionString(sectionString)

        assertAll(
            { objectUnderTest.lowerSection.shouldBe(expectedLowerBond) },
            { objectUnderTest.upperSection.shouldBe(expectedUpperBond) },
            { objectUnderTest.getSections().shouldContainAll(expectedSections) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestAssignmentPair")
    fun testAssignmentPair(
        row: String,
        expectedLeftAssignment: Assignment,
        expectedRightAssignment: Assignment,
        expectedOneAssignmentContainsTheOther: Boolean,
        expectedOneAssignmentOverlapsTheOther: Boolean
    ) {
        val objectUnderTest: AssignmentPair = AssignmentPair.processInputRow(row)

        assertAll(
            { objectUnderTest.leftAssignment.shouldBe(expectedLeftAssignment) },
            { objectUnderTest.rightAssignment.shouldBe(expectedRightAssignment) },
            { objectUnderTest.oneAssignmentContainsTheOther().shouldBe(expectedOneAssignmentContainsTheOther) },
            { objectUnderTest.oneAssignmentOverlapsTheOther().shouldBe(expectedOneAssignmentOverlapsTheOther) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestFindAssignmentPairsWithOneAssignmentContainingTheOther")
    fun testFindAssignmentPairsWithOneAssignmentContainingTheOther(
        filename: String,
        expectedNumberOfContainedAssignments: Int,
        expectedNumberOfOverlappingAssignments: Int
    ) =
        assertAll(
            {
                CampCleanup(filename).findAssignmentPairsWithOneAssignmentContainingTheOther()
                    .shouldBe(expectedNumberOfContainedAssignments)
            },
            {
                CampCleanup(filename).findAssignmentPairsWithOneAssignmentOverlappingTheOther()
                    .shouldBe(expectedNumberOfOverlappingAssignments)
            }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestAssignment(): Stream<Arguments> =
            Stream.of(
                Arguments.of("2-4", 2, 4, listOf(2, 3, 4)),
                Arguments.of("2-3", 2, 3, listOf(2, 3)),
                Arguments.of("5-7", 5, 7, listOf(5, 6, 7)),
                Arguments.of("2-8", 2, 8, listOf(2, 3, 4, 5, 6, 7, 8)),
                Arguments.of("6-6", 6, 6, listOf(6)),
                Arguments.of("2-6", 2, 6, listOf(2, 3, 4, 5, 6)),
                Arguments.of("6-8", 6, 8, listOf(6, 7, 8)),
                Arguments.of("4-5", 4, 5, listOf(4, 5)),
                Arguments.of("7-9", 7, 9, listOf(7, 8, 9)),
                Arguments.of("3-7", 3, 7, listOf(3, 4, 5, 6, 7)),
                Arguments.of("4-6", 4, 6, listOf(4, 5, 6)),
                Arguments.of("4-8", 4, 8, listOf(4, 5, 6, 7, 8)),
                Arguments.of("2-1", 1, 2, listOf(1, 2))
            )

        @JvmStatic
        private fun getDataForTestAssignmentPair(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "2-4,6-8",
                    Assignment.processSectionString("2-4"),
                    Assignment.processSectionString("6-8"),
                    false,
                    false
                ),
                Arguments.of(
                    "2-3,4-5",
                    Assignment.processSectionString("2-3"),
                    Assignment.processSectionString("4-5"),
                    false,
                    false
                ),
                Arguments.of(
                    "5-7,7-9",
                    Assignment.processSectionString("5-7"),
                    Assignment.processSectionString("7-9"),
                    false,
                    true
                ),
                Arguments.of(
                    "2-8,3-7",
                    Assignment.processSectionString("2-8"),
                    Assignment.processSectionString("3-7"),
                    true,
                    true
                ),
                Arguments.of(
                    "6-6,4-6",
                    Assignment.processSectionString("6-6"),
                    Assignment.processSectionString("4-6"),
                    true,
                    true
                ),
                Arguments.of(
                    "2-6,4-8",
                    Assignment.processSectionString("2-6"),
                    Assignment.processSectionString("4-8"),
                    false,
                    true
                )
            )

        @JvmStatic
        private fun getDataForTestFindAssignmentPairsWithOneAssignmentContainingTheOther(): Stream<Arguments> =
            Stream.of(
                Arguments.of("sampleRow1.txt", 0, 0),
                Arguments.of("sampleRow2.txt", 0, 0),
                Arguments.of("sampleRow3.txt", 0, 1),
                Arguments.of("sampleRow4.txt", 1, 1),
                Arguments.of("sampleRow5.txt", 1, 1),
                Arguments.of("sampleRow6.txt", 0, 1),
                Arguments.of("sample.txt", 2, 4)
            )
    }
}

private fun Int.shouldBe(expectation: Int) = Assertions.assertEquals(expectation, this)
private fun List<Int>.shouldContainAll(expectation: Collection<Int>) =
    Assertions.assertTrue(this.containsAll(expectation))

private fun Assignment.shouldBe(expectation: Assignment) = Assertions.assertEquals(expectation, this)
private fun Boolean.shouldBe(expectation: Boolean) = Assertions.assertEquals(expectation, this)