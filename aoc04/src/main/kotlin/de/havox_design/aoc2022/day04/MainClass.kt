package de.havox_design.aoc2022.day04

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Assignments containing the other: ${CampCleanup("input.txt").findAssignmentPairsWithOneAssignmentContainingTheOther()}")
            println("Assignments overlapping the other: ${CampCleanup("input.txt").findAssignmentPairsWithOneAssignmentOverlappingTheOther()}")
        }
    }
}
