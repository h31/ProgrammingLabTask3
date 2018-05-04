package logic

import logic.Board.*

interface Checkers {
    val board: Board
    val playerWhite: Player
    val playerBlack: Player
    var turn: Side
    var selectedChecker: Position?
    var needCapture: Boolean

    fun selectChecker(position: Position): List<Move>
    fun moveChecker(position: Position)

    fun changeTurn() {
        turn = if (turn == Side.WHITE) Side.BLACK
        else Side.WHITE
        selectedChecker = null
        needCapture = board.isCaptureNeed(turn)
    }

    fun checkWinner()
}