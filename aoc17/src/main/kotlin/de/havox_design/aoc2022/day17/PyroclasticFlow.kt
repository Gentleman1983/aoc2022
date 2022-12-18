package de.havox_design.aoc2022.day17

class PyroclasticFlow(private var filename: String) {
    val data = readFile()
    private val rockSequence = arrayOf(Rock.HORIZONTAL_LINE, Rock.PLUS, Rock.ARROW, Rock.VERTICAL_LINE, Rock.BOX)

    fun processPart1(): Int =
        0

    fun processPart2(): Int =
        0

    private fun readFile(): List<Jet> {
        val fileData = getResourceAsText(filename)
        val jetSequence = emptyList<Jet>().toMutableList()

        for(index in fileData[0].indices) {
            val code: String = fileData[0][index].toString()

            jetSequence.add(Jet.getJetForCode(code))
        }

        return jetSequence
    }


    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()
}
