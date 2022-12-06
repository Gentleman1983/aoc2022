package de.havox_design.aoc2022.day06

data class DetectionWindow(val data: String) {
    private val windowLength = 4
    fun isPacketMarker(): Boolean {
        var isMarker = true

        if( hasCorrectLength() ) {
            val charMap = emptySet<Char>().toMutableSet()

            for(c in data.toCharArray()) {
                if(charMap.contains(c)) {
                    isMarker = false
                    break;
                }

                charMap += c
            }
        }
        else {
            isMarker = false
        }

        return isMarker
    }

    fun hasCorrectLength() = data.length == windowLength
}
