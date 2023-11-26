package de.havox_design.aoc2022.day02

class RockPaperScissorsGame(private val filename: String) {
    fun getResultForGuide(): Int {
        var score = 0

        val turns: List<String>? = getResourceAsText(filename)

        if (!turns.isNullOrEmpty()) {
            for (index in turns.indices) {
                val actions: List<String> = turns[index].split(" ")

                if (actions.isNotEmpty()) {
                    val opponent: RockPaperScissorsFigures = RockPaperScissorsFigures.getValueBySymbol(actions[0])
                    val turn: RockPaperScissorsFigures = RockPaperScissorsFigures.getValueBySymbol(actions[1])
                    score += turn.score

                    score += getScoreByResult(opponent, turn)
                }
            }
        }

        return score
    }

    fun getResultForExpectedResult(): Int {
        var score = 0

        val turns: List<String>? = getResourceAsText(filename)

        if (!turns.isNullOrEmpty()) {
            for (index in turns.indices) {
                val actions: List<String> = turns[index].split(" ")

                if (actions.isNotEmpty()) {
                    val opponent: RockPaperScissorsFigures = RockPaperScissorsFigures.getValueBySymbol(actions[0])
                    val expectedResult: RockPaperScissorsResult = RockPaperScissorsResult.getValueBySymbol(actions[1])
                    val turn: RockPaperScissorsFigures = getTurnByExpectedResult(opponent, expectedResult)
                    score += turn.score

                    score += getScoreByResult(opponent, turn)
                }
            }
        }

        return score
    }

    @SuppressWarnings("kotlin:S6511")
    private fun getScoreByResult(opponentTurn: RockPaperScissorsFigures, yourTurn: RockPaperScissorsFigures): Int {
        return if (opponentTurn == RockPaperScissorsFigures.ROCK && yourTurn == RockPaperScissorsFigures.SCISSORS) {
            RockPaperScissorsResult.LOSS.score
        } else if (opponentTurn == RockPaperScissorsFigures.ROCK && yourTurn == RockPaperScissorsFigures.PAPER) {
            RockPaperScissorsResult.WIN.score
        } else if (opponentTurn == RockPaperScissorsFigures.PAPER && yourTurn == RockPaperScissorsFigures.ROCK) {
            RockPaperScissorsResult.LOSS.score
        } else if (opponentTurn == RockPaperScissorsFigures.PAPER && yourTurn == RockPaperScissorsFigures.SCISSORS) {
            RockPaperScissorsResult.WIN.score
        } else if (opponentTurn == RockPaperScissorsFigures.SCISSORS && yourTurn == RockPaperScissorsFigures.ROCK) {
            RockPaperScissorsResult.WIN.score
        } else if (opponentTurn == RockPaperScissorsFigures.SCISSORS && yourTurn == RockPaperScissorsFigures.PAPER) {
            RockPaperScissorsResult.LOSS.score
        } else {
            RockPaperScissorsResult.DRAW.score
        }
    }

    @SuppressWarnings("kotlin:S6510", "kotlin:S6511")
    private fun getTurnByExpectedResult(
        opponentTurn: RockPaperScissorsFigures,
        expectedResult: RockPaperScissorsResult
    ): RockPaperScissorsFigures {
        if (expectedResult == RockPaperScissorsResult.DRAW) {
            return opponentTurn
        } else if (opponentTurn == RockPaperScissorsFigures.ROCK) {
            return if (expectedResult == RockPaperScissorsResult.WIN) {
                RockPaperScissorsFigures.PAPER
            } else {
                RockPaperScissorsFigures.SCISSORS
            }
        } else if (opponentTurn == RockPaperScissorsFigures.PAPER) {
            return if (expectedResult == RockPaperScissorsResult.WIN) {
                RockPaperScissorsFigures.SCISSORS
            } else {
                RockPaperScissorsFigures.ROCK
            }
        } else {
            return if (expectedResult == RockPaperScissorsResult.WIN) {
                RockPaperScissorsFigures.ROCK
            } else {
                RockPaperScissorsFigures.PAPER
            }
        }
    }


    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}
