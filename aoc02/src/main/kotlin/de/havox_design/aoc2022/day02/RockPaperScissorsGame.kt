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


    private fun getResourceAsText(path: String): List<String>? =
        this.javaClass.classLoader.getResourceAsStream(path)?.bufferedReader()?.readLines()
}