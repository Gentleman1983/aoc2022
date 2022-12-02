package de.havox_design.aoc2022.day02

class RockPaperScissorsGame(val filename: String) {
    fun getResultForGuide(): Int {
        var score: Int = 0

        var turns: List<String>? = getResourceAsText(filename)

        if(!turns.isNullOrEmpty()) {
            for (index in turns.indices) {
                var actions: List<String>? = turns[index].split(" ")

                if(!actions.isNullOrEmpty()) {
                    var opponent: RockPaperScissorsFigures = RockPaperScissorsFigures.getValueBySymbol(actions[0])
                    var turn: RockPaperScissorsFigures = RockPaperScissorsFigures.getValueBySymbol(actions[1])
                    score += turn.score

                    score += getScoreByResult(opponent, turn)
                }
            }
        }

        return score
    }

    fun getResultForExpectedResult(): Int {
        var score: Int = 0

        var turns: List<String>? = getResourceAsText(filename)

        if(!turns.isNullOrEmpty()) {
            for (index in turns.indices) {
                var actions: List<String>? = turns[index].split(" ")

                if(!actions.isNullOrEmpty()) {
                    var opponent: RockPaperScissorsFigures = RockPaperScissorsFigures.getValueBySymbol(actions[0])
                    var expectedResult: RockPaperScissorsResult = RockPaperScissorsResult.getValueBySymbol(actions[1])
                    var turn: RockPaperScissorsFigures = getTurnByExpectedResult(opponent, expectedResult)
                        score += turn.score

                    score += getScoreByResult(opponent, turn)
                }
            }
        }

        return score
    }

    private fun getScoreByResult(opponentTurn: RockPaperScissorsFigures, yourTurn: RockPaperScissorsFigures): Int {
        if(opponentTurn == RockPaperScissorsFigures.ROCK && yourTurn == RockPaperScissorsFigures.SCISSORS) {
            return RockPaperScissorsResult.LOSS.score
        }
        else if(opponentTurn == RockPaperScissorsFigures.ROCK && yourTurn == RockPaperScissorsFigures.PAPER) {
            return RockPaperScissorsResult.WIN.score
        }
        else if(opponentTurn == RockPaperScissorsFigures.PAPER && yourTurn == RockPaperScissorsFigures.ROCK) {
            return RockPaperScissorsResult.LOSS.score
        }
        else if(opponentTurn == RockPaperScissorsFigures.PAPER && yourTurn == RockPaperScissorsFigures.SCISSORS) {
            return RockPaperScissorsResult.WIN.score
        }
        else if(opponentTurn == RockPaperScissorsFigures.SCISSORS && yourTurn == RockPaperScissorsFigures.ROCK) {
            return RockPaperScissorsResult.WIN.score
        }
        else if(opponentTurn == RockPaperScissorsFigures.SCISSORS && yourTurn == RockPaperScissorsFigures.PAPER) {
            return RockPaperScissorsResult.LOSS.score
        }
        else {
            return RockPaperScissorsResult.DRAW.score
        }
    }

    private fun getTurnByExpectedResult(opponentTurn: RockPaperScissorsFigures, expectedResult: RockPaperScissorsResult): RockPaperScissorsFigures {
        if(expectedResult == RockPaperScissorsResult.DRAW) {
            return opponentTurn
        }
        else if(opponentTurn == RockPaperScissorsFigures.ROCK) {
            if(expectedResult == RockPaperScissorsResult.WIN) {
                return  RockPaperScissorsFigures.PAPER
            }
            else {
                return RockPaperScissorsFigures.SCISSORS
            }
        }
        else if(opponentTurn == RockPaperScissorsFigures.PAPER) {
            if(expectedResult == RockPaperScissorsResult.WIN) {
                return RockPaperScissorsFigures.SCISSORS
            }
            else {
                return RockPaperScissorsFigures.ROCK
            }
        }
        else {
            if(expectedResult == RockPaperScissorsResult.WIN) {
                return RockPaperScissorsFigures.ROCK
            }
            else {
                return RockPaperScissorsFigures.PAPER
            }
        }
    }


    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}