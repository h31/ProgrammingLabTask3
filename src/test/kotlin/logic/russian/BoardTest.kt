package logic.russian

import logic.Board.Move
import logic.Board.Position
import logic.MoveType.CAPTURE
import logic.MoveType.MOVE
import logic.Side
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest {
    val board = RussianBoard()

    fun printBoard() {
        println(board.toString() + "\n")
    }

    @Test
    fun createBoard() {
        printBoard()
        assertEquals(setOf<Move>(), board.possibleCaptures(Position(2, 3)))

        board.moveChecker(Position(1, 6), Move(Position(2, 5), MOVE))
        printBoard()
        assertEquals(setOf<Move>(), board.possibleCaptures(Position(2, 3)))

        board.moveChecker(Position(2, 5), Move(Position(3, 4), MOVE))
        printBoard()
        assertEquals(setOf(Move(Position(4, 5), CAPTURE, Position(3, 4))), board.possibleCaptures(Position(2, 3)))
        assertEquals(setOf<Move>(), board.possibleCaptures(Position(3, 4)))

        board.moveChecker(Position(2, 3), board.possibleCaptures(Position(2, 3)).first())
        printBoard()
        assertEquals(setOf(Move(Position(3, 4), CAPTURE, Position(4, 5))), board.possibleCaptures(Position(5, 6)))

        assertTrue(board.isCaptureNeed(Side.WHITE))
        assertFalse(board.isCaptureNeed(Side.BLACK))
    }
}