package logic.russian

import logic.Board.*
import logic.MoveType.*
import logic.Side
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BoardTest {

    @Test
    fun createBoard() {
        val board = RussianBoard()

        println(board.toString() + "\n")
        assertFalse(board.canCapture(Position(2, 3)))

        board.moveChecker(Position(1, 6), Move(Position(2, 5), MOVE))
        println(board.toString() + "\n")
        assertFalse(board.canCapture(Position(2, 3)))

        board.moveChecker(Position(2, 5), Move(Position(3, 4), MOVE))
        println(board.toString() + "\n")
        assertTrue(board.canCapture(Position(2, 3)))
        assertFalse(board.canCapture(Position(3, 4)))

        board.moveChecker(Position(2, 3), Move(Position(4, 5), CAPTURE))
        println(board.toString() + "\n")
        assertTrue(board.canCapture(Position(5, 6)))

        assertTrue(board.isCaptureNeed(Side.WHITE))
        assertFalse(board.isCaptureNeed(Side.BLACK))
    }
}