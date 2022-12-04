package de.havox_design.aoc2022.day04

data class AssignmentPair(val leftAssignment: Assignment, val rightAssignment: Assignment) {
    companion object {
        fun processInputRow(data: String): AssignmentPair {
            val cleanUpSections = data.split(",")
            val leftAssignment = Assignment.processSectionString(cleanUpSections[0])
            val rightAssignment = Assignment.processSectionString(cleanUpSections[1])

            return AssignmentPair(leftAssignment, rightAssignment)
        }
    }
}
