package de.havox_design.aoc2022.day04

data class AssignmentPair(val leftAssignment: Assignment, val rightAssignment: Assignment) {
    fun oneAssignmentContainsTheOther(): Boolean =
        ((leftAssignment.lowerSection <= rightAssignment.lowerSection) && (leftAssignment.upperSection >= rightAssignment.upperSection)) ||
                ((rightAssignment.lowerSection <= leftAssignment.lowerSection) && (rightAssignment.upperSection >= leftAssignment.upperSection))

    fun oneAssignmentOverlapsTheOther(): Boolean =
        leftAssignment.getSections().any { rightAssignment.getSections().contains(it) }

    companion object {
        fun processInputRow(data: String): AssignmentPair {
            val cleanUpSections = data.split(",")
            val leftAssignment = Assignment.processSectionString(cleanUpSections[0])
            val rightAssignment = Assignment.processSectionString(cleanUpSections[1])

            return AssignmentPair(leftAssignment, rightAssignment)
        }
    }
}
