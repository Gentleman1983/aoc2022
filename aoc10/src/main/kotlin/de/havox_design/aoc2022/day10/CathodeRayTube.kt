package de.havox_design.aoc2022.day10

class CathodeRayTube(private var filename: String, var registerX: Int = 1) {
    val instructions = readFile()
    private var cycle = 0
    private val registerXLog = emptyMap<Int, Int>().toMutableMap()

    @SuppressWarnings("kotlin:S6611")
    fun processPart1(): Int {
        for (instruction in instructions) {
            cycle++

            if (instruction.order == Order.ADDX) {
                registerXLog[cycle] = registerX
                cycle++
                registerXLog[cycle] = registerX
                registerX += instruction.parameter
            } else if (instruction.order == Order.NOOP) {
                registerXLog[cycle] = registerX
            }
        }

        var sum = 0
        for (entry in listOf(20, 60, 100, 140, 180, 220)) {
            if (registerXLog.keys.contains(entry)) {
                sum += registerXLog[entry]!! * entry
            }
        }

        return sum
    }

    @SuppressWarnings("kotlin:S6611")
    fun processPart2(): String {
        processPart1()
        var output = ""

        for (index in 0..239) {
            if (index != 0 && index % 40 == 0) {
                output += "\n"
            }

            output += Sprite(registerXLog[index + 1]!!).getValueForPostion(index % 40)
        }

        return output
    }

    private fun readFile(): List<Instruction> {
        val instructionList = emptyList<Instruction>().toMutableList()
        val fileData = getResourceAsText(filename)

        for (row in fileData) {
            if (row.startsWith(Order.NOOP.orderName)) {
                instructionList += Instruction(Order.NOOP)
            } else if (row.startsWith(Order.ADDX.orderName)) {
                val parameter = row.split(" ")[1].toInt()

                instructionList += Instruction(Order.ADDX, parameter)
            }
        }

        return instructionList
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
