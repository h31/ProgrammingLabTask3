package logic

import logic.Board.Move
import logic.Board.Position

interface Checkers {
    val playerWhite: Player
    val playerBlack: Player

    fun selectChecker(position: Position): Set<Move>
    fun moveChecker(move: Move)
    fun checkWinner(): Player?
}