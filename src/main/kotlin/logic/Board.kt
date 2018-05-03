package logic

interface Board {
    val board: Array<Array<Checker?>>

    fun checkChecker(position: Position): Checker?
    fun canCapture(position: Position): Boolean
    fun isCaptureNeed(side: Side): Boolean
    fun possibleMoves(position: Position): List<Move>
    fun moveChecker(move: Move): MoveType
    fun randomMove(side: Side): MoveType

    class Position(val x: Int, val y: Int)
    class Move(val position: Position, val moveType: MoveType)
}