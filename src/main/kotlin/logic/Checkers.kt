package logic

import logic.Board.Move
import logic.Board.Position

interface Checkers {
    val playerWhite: Player
    val playerBlack: Player

    fun selectChecker(position: Position): Set<Move>
    /**
     * @param move must be a Move recieved from selectChecker method
     * @return Position of captured checker or null if it's move without capturing
     */
    fun moveChecker(move: Move): Position?

    fun checkWinner(): Player?
}