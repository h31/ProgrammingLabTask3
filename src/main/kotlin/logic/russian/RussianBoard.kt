package logic.russian

import logic.Board
import logic.Board.Move
import logic.Board.Position
import logic.Checker
import logic.MoveType
import logic.MoveType.CAPTURE
import logic.MoveType.MOVE
import logic.Side
import logic.Side.BLACK
import logic.Side.WHITE

class RussianBoard : Board {
    override val boardSize = 8
    val board: Array<Array<Checker?>> = Array(boardSize, { i -> arrayOfNulls<Checker?>(boardSize) })
    val whiteDirections = setOf(Position(1, -1), Position(-1, -1))
    val blackDirections = setOf(Position(1, 1), Position(-1, 1))

    operator fun get(row: Int): Array<Checker?> = board[row]

    init {
        for (row in 0..(boardSize - 1)) {
            for (col in 0..(boardSize - 1)) {
                if (((row == 0 || row == 2) && col % 2 == 1) || row == 1 && col % 2 == 0)
                    board[row][col] = Checker(BLACK)
                else if (((row == 5 || row == 7) && col % 2 == 0) || row == 6 && col % 2 == 1)
                    board[row][col] = Checker(WHITE)
            }
        }
    }

    override fun checkChecker(position: Position): Checker? =
            board[position.y - 1][position.x - 1]

    override fun possibleCaptures(position: Position): Set<Move> {
        val checker: Checker = checkChecker(position)
                ?: throw IllegalArgumentException("It must be checker on position")
        val result = mutableSetOf<Move>()

        for (direction in whiteDirections + blackDirections) {
            var nextPosition = position

            while (true) {
                nextPosition += direction
                if (isPositionOnBoard(nextPosition)) {
                    val nextChecker = checkChecker(nextPosition)
                    if (nextChecker == null) {
                        if (checker.isKing) continue
                    } else if (nextChecker.side != checker.side) {
                        var nextCapture = nextPosition + direction
                        while (true) {
                            if (isPositionOnBoard(nextCapture) && checkChecker(nextCapture) == null)
                                result.add(Move(nextCapture, CAPTURE, nextPosition))
                            else break
                            if (!checker.isKing) break
                            nextCapture += direction
                        }
                    }
                    break
                } else break
            }

        }

        return result
    }

    override fun isCaptureNeed(side: Side): Boolean {
        for (row in 0..(boardSize - 1)) {
            for (col in 0..(boardSize - 1)) {
                if (board[row][col]?.side == side)
                    if (!possibleCaptures(Position(col + 1, row + 1)).isEmpty()) return true
            }
        }
        return false
    }

    override fun possibleMoves(position: Position): Set<Move> {
        val checker = checkChecker(position) ?: throw IllegalArgumentException("It must be checker on position")
        val side = checker.side
        val result = mutableSetOf<Move>()

        for (direction in if (checker.isKing) whiteDirections + blackDirections
        else if (side == WHITE) whiteDirections else blackDirections) {
            var nextPosition = position

            while (true) {
                nextPosition += direction
                if (isPositionOnBoard(nextPosition)) {
                    val nextChecker = checkChecker(nextPosition)
                    if (nextChecker == null) {
                        result.add(Move(nextPosition, MOVE))
                        if (checker.isKing) continue
                    }
                    break
                } else break
            }
        }
        return (result + possibleCaptures(position)).toSet()
    }

    override fun moveChecker(position: Position, move: Move): Position? {
        val checker = board[position.y - 1][position.x - 1]!!
        if (move.position.y == 1 && checker.side == WHITE || move.position.y == 8 && checker.side == BLACK)
            checker.isKing = true
        board[move.position.y - 1][move.position.x - 1] = checker

        if (move.moveType == CAPTURE) {
            board[move.capturedPosition!!.y - 1][move.capturedPosition.x - 1] = null
        }

        board[position.y - 1][position.x - 1] = null
        return move.capturedPosition
    }

    override fun randomMove(side: Side): MoveType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun isPositionOnBoard(position: Position): Boolean =
            position.x in 1..boardSize && position.y in 1..boardSize

    override fun toString(): String {
        val result = StringBuilder()
        for (row in 0..(boardSize - 1)) {
            result.appendln("-----------------")
            result.append("|")
            for (col in 0..(boardSize - 1)) {
                val currentChecker = board[row][col]
                result.append(if (currentChecker == null) " " else if (currentChecker.side == WHITE)
                    if (currentChecker.isKing) "̃W" else "W"
                else if (currentChecker.isKing) "̃B" else "B")
                result.append("|")
            }
            result.appendln()
        }
        result.append("-----------------")
        return result.toString()
    }
}