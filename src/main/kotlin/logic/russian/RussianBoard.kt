package logic.russian

import logic.*
import logic.Side.*
import logic.Board.*
import logic.MoveType.*

class RussianBoard : Board {
    override val boardSize = 8
    val board: Array<Array<Checker?>> = Array(boardSize, { i -> arrayOfNulls<Checker?>(boardSize) })
    val whiteDirections = setOf(Position(1, -1), Position(-1, -1))
    val blackDirections = setOf(Position(1, 1), Position(-1, 1))

    init {
        for (row in 0..(boardSize - 1)) {
            for (col in 0..(boardSize - 1)) {
                if (((row == 0 || row == 2) && col % 2 == 1) || row == 1 && col % 2 == 0)
                    board[row][col] = Checker(BLACK)
                if (((row == 5 || row == 7) && col % 2 == 0) || row == 6 && col % 2 == 1)
                    board[row][col] = Checker(WHITE)
            }
        }
    }

    override fun checkChecker(position: Position): Checker? =
            board[position.y - 1][position.x - 1]

    override fun canCapture(position: Position): Boolean {
        val side = checkChecker(position)?.side
                ?: throw IllegalArgumentException("It must be checker on position")

        for (direction in if (side == WHITE) whiteDirections else blackDirections) {
            val nextPosition = position + direction

            if (isPositionOnBoard(nextPosition)) {
                val nextChecker = checkChecker(nextPosition) ?: continue
                if (nextChecker.side != side) {
                    val endPosition = nextPosition + direction
                    if (isPositionOnBoard(endPosition) && checkChecker(endPosition) == null) return true
                }
            }

        }

        return false
    }

    override fun isCaptureNeed(side: Side): Boolean {
        for (row in 0..(boardSize - 1)) {
            for (col in 0..(boardSize - 1)) {
                if (board[row][col]?.side == side)
                    if (canCapture(Position(col + 1, row + 1))) return true
            }
        }
        return false
    }

    override fun possibleMoves(position: Position): List<Move> {
        val checker = checkChecker(position) ?: throw IllegalArgumentException("It must be checker on position")
        val side = checker.side
        val result = mutableListOf<Move>()

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
                    } else if (nextChecker.side != side) {
                        val nextCapture = nextPosition + direction
                        if (isPositionOnBoard(nextCapture) && checkChecker(nextCapture) == null)
                            result.add(Move(nextCapture, CAPTURE))
                    }
                    break
                } else break
            }
        }
        return result.toList()
    }

    override fun moveChecker(position: Position, move: Move): MoveType {
        board[move.position.y - 1][move.position.x - 1] = board[position.y - 1][position.x - 1]
        if (move.moveType != MOVE)
            board[(position.y + move.position.y) / 2 - 1][(position.x + move.position.x) / 2 - 1] = null
        board[position.y - 1][position.x - 1] = null
        return move.moveType
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
                result.append(if (currentChecker == null) " " else if (currentChecker.side == WHITE) "W" else "B")
                result.append("|")
            }
            result.appendln()
        }
        result.append("-----------------")
        return result.toString()
    }
}