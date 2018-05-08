package logic.russian

import logic.Board.Move
import logic.Board.Position
import logic.Checkers
import logic.MoveType
import logic.MoveType.CAPTURE
import logic.Player
import logic.Side.BLACK
import logic.Side.WHITE

class RussianCheckers : Checkers {
    val board = RussianBoard()
    override val playerWhite = RussianPlayer(WHITE, "white_player")
    override val playerBlack = RussianPlayer(BLACK, "black_player")

    var turn = WHITE
    var selectedChecker: Position = Position(1, 1)

    override fun selectChecker(position: Position): Set<Move> {
        selectedChecker = position
        val checker = board.checkChecker(position)
        if (checker == null || checker.side != turn) return emptySet()
        else return if (board.isCaptureNeed(turn)) board.possibleMoves(position).filter { it.moveType == CAPTURE }.toSet()
        else board.possibleMoves(position)
    }

    fun selectChecker(x: Int, y: Int) = selectChecker(Position(x, y))

    /**
     * @param move must be a Move recieved from selectChecker method
     */
    override fun moveChecker(move: Move) {
        board.moveChecker(selectedChecker, move)
        if (move.moveType == CAPTURE) {
            if (turn == WHITE) playerBlack.lostCheckers++
            else playerWhite.lostCheckers++
            if (!board.isCaptureNeed(turn)) changeTurn()
        } else changeTurn()
    }

    fun moveChecker(x: Int, y: Int, moveType: MoveType) = moveChecker(Move(x, y, moveType))

    override fun checkWinner(): Player? =
            if (playerBlack.lostCheckers == 12) playerWhite
            else if (playerWhite.lostCheckers == 12) playerBlack
            else null

    fun changeTurn() {
        turn = if (turn == WHITE) BLACK
        else WHITE
    }
}