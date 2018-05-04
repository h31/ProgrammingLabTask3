package logic

interface Board {
    val boardSize: Int

    fun checkChecker(position: Position): Checker?
    fun canCapture(position: Position): Boolean
    fun isCaptureNeed(side: Side): Boolean
    fun possibleMoves(position: Position): List<Move>
    fun moveChecker(position: Position, move: Move): MoveType
    fun randomMove(side: Side): MoveType

    /**
     * Coordinates started from 1!
     */
    class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position): Position =
                Position(x + other.x, y + other.y)
    }

    class Move(val position: Position, val moveType: MoveType)
}