package de.havox_design.aoc2022.day04

data class Assignment(val lowerSection: Int, val upperSection: Int) {
    fun getSections(): List<Int> {
        var result: List<Int> = emptyList()

        for(index in lowerSection..upperSection) {
            result += index
        }

        return result
    }

    companion object {
        fun processSectionString(s: String): Assignment {
            val sections = s.split("-")

            var lowerBound: Int = sections[0].toInt()
            var upperBound: Int = sections[1].toInt()

            if(lowerBound > upperBound) {
                val tmp = lowerBound
                lowerBound = upperBound
                upperBound = tmp
            }

            return Assignment(lowerBound, upperBound)
        }
    }
}
