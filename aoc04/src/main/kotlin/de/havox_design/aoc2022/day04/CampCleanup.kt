package de.havox_design.aoc2022.day04

class CampCleanup(private val filename: String) {
    fun findAssignmentPairsWithOneAssignmentContainingTheOther(): Int {
        val assignmentPairs = readAssignmentPairs()
        var number = 0

        for (assignmentPair in assignmentPairs) {
            if (assignmentPair.oneAssignmentContainsTheOther()) {
                number++
            }
        }

        return number
    }

    fun findAssignmentPairsWithOneAssignmentOverlappingTheOther(): Int {
        val assignmentPairs = readAssignmentPairs()
        var number = 0

        for (assignmentPair in assignmentPairs) {
            if (assignmentPair.oneAssignmentOverlapsTheOther()) {
                number++
            }
        }

        return number
    }

    private fun readAssignmentPairs(): List<AssignmentPair> {
        val dataRows = getResourceAsText(filename)
        val assignmentPairs = emptyList<AssignmentPair>().toMutableList()

        if (!dataRows.isNullOrEmpty()) {
            for (row in dataRows) {
                assignmentPairs += AssignmentPair.processInputRow(row)
            }
        }

        return assignmentPairs
    }

    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}
