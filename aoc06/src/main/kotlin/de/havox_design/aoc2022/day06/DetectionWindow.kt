package de.havox_design.aoc2022.day06

data class DetectionWindow(val data: String) {
    fun isMessageMarker(): Boolean = isMarker(14)
    fun isPacketMarker(): Boolean = isMarker(4)
    fun hasCorrectLength(windowLength: Int) = data.length == windowLength

    private fun isMarker(windowLength: Int): Boolean {
        var isMarker = true

        if (hasCorrectLength(windowLength)) {
            val charMap = emptySet<Char>().toMutableSet()

            for (c in data.toCharArray()) {
                if (charMap.contains(c)) {
                    isMarker = false
                    break;
                }

                charMap += c
            }
        } else {
            isMarker = false
        }

        return isMarker
    }
}
