package de.havox_design.aoc2022.day25

class MainClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val inputFileName = "input.txt"

            val part1Result = FullOfHotAir(inputFileName).processPart1()
            println("SNAFU number: ${SNAFUNumber.toSnafu(part1Result).toReadableString()} ($part1Result)")
        }
    }
}

private fun List<SNAFUNumber>.toReadableString(): String {
    var result = ""

    for(digit in this) {
        result += digit.toSnafuSymbol()
    }

    return result
}
