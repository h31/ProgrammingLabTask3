package logic.russian

import logic.Board.*
import logic.MoveType.*
import logic.Side
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BoardTest {
    val board = RussianBoard()

    fun printBoard() {
        println(board.toString() + "\n")
    }

    @Test
    fun createBoard() {
        printBoard()
        assertFalse(board.canCapture(Position(2, 3)))

        board.moveChecker(Position(1, 6), Move(Position(2, 5), MOVE))
        printBoard()
        assertFalse(board.canCapture(Position(2, 3)))

        board.moveChecker(Position(2, 5), Move(Position(3, 4), MOVE))
        printBoard()
        assertTrue(board.canCapture(Position(2, 3)))
        assertFalse(board.canCapture(Position(3, 4)))

        board.moveChecker(Position(2, 3), Move(Position(4, 5), CAPTURE))
        printBoard()
        assertTrue(board.canCapture(Position(5, 6)))

        assertTrue(board.isCaptureNeed(Side.WHITE))
        assertFalse(board.isCaptureNeed(Side.BLACK))
    }
}