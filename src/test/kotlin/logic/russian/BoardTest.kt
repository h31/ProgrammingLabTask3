package logic.russian

import logic.Board.*
import logic.MoveType
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun createBoard() {
        val board = RussianBoard()

        println(board.toString())
        println(board.canCapture(Position(2, 3)))

        board.moveChecker(Position(1, 6), Move(Position(2, 5), MoveType.MOVE))
        println(board)
        println(board.canCapture(Position(2, 3)))

        board.moveChecker(Position(2, 5), Move(Position(3, 4), MoveType.MOVE))
        println(board)
        println(board.canCapture(Position(2, 3)))
        println(board.canCapture(Position(3, 4)))

        board.moveChecker(Position(2, 3), Move(Position(4, 5), MoveType.END_CAPTURE))
        println(board)

        println(board.canCapture(Position(5, 6)))
    }
}