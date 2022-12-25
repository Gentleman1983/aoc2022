package de.havox_design.aoc2022.day22

import java.io.File

class MonkeyMap(private var filename: String) {
    val data = readFile()
    val orders = readOrders()

    fun processPart1(): Int {
        val map = Map(data)

        // perform orders
        for(order in orders) {
            if(order.isMove()) {
                map.move(order.toInt())
            }
            else {
                when(order) {
                    "R" -> map.turnRight()
                    "L" -> map.turnLeft()
                }
            }
        }

        return map.evaluatePosition()
    }

    fun processPart2(): Int =
        MonkeyCubeSolver().solvePart2(File("src/main/resources/input.txt"))

    private fun readFile(): List<String> {
        val input = getResourceAsText(filename).toMutableList()
        val orderRow = input[input.size - 1]
        input -= orderRow
        // remove empty row at the end
        input.removeAt(input.size - 1)

        return input
    }


    private fun readOrders(): List<String> {
        val input = getResourceAsText(filename)
        val orderRow = input[input.size - 1]

        return parseOrders(orderRow)
    }

    private fun parseOrders(orderRow: String): List<String> {
        val orders = emptyList<String>().toMutableList()
        var isFirstLetter = true
        var isDigit = false
        var currentOrder = ""

        for(char in orderRow){
            if(isFirstLetter) {
                isDigit = char.isDigit()
                isFirstLetter = false
            }

            val currentCharIsDigit = char.isDigit()
            if(currentCharIsDigit != isDigit) {
                orders += currentOrder
                currentOrder = ""
                isDigit = currentCharIsDigit
            }

            currentOrder += char.toString()
        }
        orders += currentOrder

        return orders
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    private fun String.isMove(): Boolean =
        this.isEmpty() || this[0].isDigit()
}
