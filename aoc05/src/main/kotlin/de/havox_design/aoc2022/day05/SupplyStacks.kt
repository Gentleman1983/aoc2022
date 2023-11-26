package de.havox_design.aoc2022.day05

import java.util.LinkedList

class SupplyStacks(private val filename: String) {
    var data: Map<Int, Stack> = emptyMap()
    var procedure: List<Step> = emptyList()

    @SuppressWarnings("kotlin:S6611")
    fun readData() {
        val rows = getResourceAsText(filename)

        if (!rows.isNullOrEmpty()) {
            var isReadingStacks = true
            val stackRows: MutableList<String> = emptyList<String>().toMutableList()

            for (row in rows) {
                if (isReadingStacks) {
                    if (row.isBlank()) {
                        isReadingStacks = false

                        // Read stack numbers
                        val lastRow = stackRows[stackRows.size - 1]
                        for (index in lastRow.indices) {
                            val currentPos = lastRow.substring(index, index + 1)

                            if (currentPos.isNotBlank()) {
                                val id = currentPos.toInt()
                                data += Pair(id, Stack.emptyStackWithId(id))
                            }
                        }

                        // Read data
                        for (index in 2..stackRows.size) {
                            val currentRow = stackRows[stackRows.size - index]

                            for (s in 1..(currentRow.length + 1) / 4) {
                                val selectionStart = 4 * s - 3
                                val selectionEnd = 4 * s - 2
                                val element = currentRow.substring(selectionStart, selectionEnd)

                                if (element.isNotBlank()) {
                                    val currentStack = data[s]
                                    currentStack!!.stack += Crate(element)
                                    data.plus(Pair(s, currentStack))
                                }
                            }
                        }
                    } else {
                        stackRows += row
                    }
                } else {
                    // Read procedure
                    val regex = Regex("\\d+")
                    // Results should Contain entries in format [numberOfItems, fromStackNumber, toStackNumber].
                    val results = regex.findAll(row).map { it.value.toInt() }.toList()

                    val numberOfElements = results[0]
                    val fromStack = data[results[1]]!!
                    val toStack = data[results[2]]!!

                    procedure += Step(numberOfElements, fromStack, toStack)
                }
            }
        }
    }

    fun followProcedure() {
        for (step in procedure) {
            for (i in 1..step.numberOfItems) {
                val topElement = step.fromStack.stack.removeLast()
                step.toStack.stack.addLast(topElement)
            }
        }
    }

    fun followProcedureCratemaster9001() {
        for (step in procedure) {
            val tmpStack: LinkedList<Crate> = LinkedList()

            for (i in 1..step.numberOfItems) {
                val topElement = step.fromStack.stack.removeLast()
                tmpStack.addLast(topElement)
            }
            for (i in 1..step.numberOfItems) {
                val topElement = tmpStack.removeLast()
                step.toStack.stack.addLast(topElement)
            }
        }
    }

    @SuppressWarnings("kotlin:S6611")
    fun evaluateTask1(): String {
        var solution = ""

        readData()
        followProcedure()

        for (index in data.keys) {
            solution += data[index]!!.stack.last.content
        }

        return solution
    }

    @SuppressWarnings("kotlin:S6611")
    fun evaluateTask2(): String {
        var solution = ""

        readData()
        followProcedureCratemaster9001()

        for (index in data.keys) {
            solution += data[index]!!.stack.last.content
        }

        return solution
    }

    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}
