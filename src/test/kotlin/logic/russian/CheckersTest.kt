package logic.russian

import logic.Board.Move
import logic.Board.Position
import logic.MoveType.CAPTURE
import logic.MoveType.MOVE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CheckersTest {
    val checkers = RussianCheckers()

    fun printBoard() {
        println(checkers.board.toString() + "\n")
    }

    @Test
    fun checkersTest() {
        printBoard()
        assertEquals(emptySet<Move>(), checkers.selectChecker(2, 1))
        assertEquals(emptySet<Move>(), checkers.selectChecker(4, 3))
        assertEquals(setOf(Move(2, 5, MOVE), Move(4, 5, MOVE)), checkers.selectChecker(3, 6))

        checkers.moveChecker(4, 5, MOVE)
        checkers.selectChecker(2, 3)
        checkers.moveChecker(3, 4, MOVE)

        printBoard()
        val moves = checkers.selectChecker(4, 5)
        assertEquals(setOf(Move(Position(2, 3), CAPTURE, Position(3, 4))), moves)

        checkers.moveChecker(moves.first())
        printBoard()
    }

    @Test
    fun doubleCaptureTest() {
        printBoard()
        checkers.selectChecker(7, 6)
        checkers.moveChecker(8, 5, MOVE)
        checkers.selectChecker(2, 3)
        checkers.moveChecker(3, 4, MOVE)
        checkers.selectChecker(5, 6)
        checkers.moveChecker(6, 5, MOVE)
        checkers.selectChecker(1, 2)
        checkers.moveChecker(2, 3, MOVE)
        checkers.selectChecker(4, 7)
        checkers.moveChecker(5, 6, MOVE)
        checkers.selectChecker(3, 4)
        checkers.moveChecker(4, 5, MOVE)
        printBoard()
        val move1 = checkers.selectChecker(5, 6).first()
        checkers.moveChecker(move1)
        val move2 = checkers.selectChecker(3, 4).first()
        checkers.moveChecker(move2)
        printBoard()
    }
}