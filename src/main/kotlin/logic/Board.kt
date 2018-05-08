package logic

interface Board {
    val boardSize: Int

    fun checkChecker(position: Position): Checker?
    fun canCapture(position: Position): Boolean
    fun isCaptureNeed(side: Side): Boolean
    fun possibleMoves(position: Position): Set<Move>
    fun moveChecker(position: Position, move: Move): MoveType
    fun randomMove(side: Side): MoveType

    /**
     * Coordinates started from 1!
     */
    class Position(val x: Int, val y: Int) {
        operator fun plus(other: Position): Position =
                Position(x + other.x, y + other.y)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Position

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

        override fun toString(): String {
            return "($x, $y)"
        }

    }

    class Move(val position: Position, val moveType: MoveType) {
        constructor(x: Int, y: Int, moveType: MoveType) : this(Position(x, y), moveType)

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Move

            if (position != other.position) return false
            if (moveType != other.moveType) return false

            return true
        }

        override fun hashCode(): Int {
            var result = position.hashCode()
            result = 31 * result + moveType.hashCode()
            return result
        }

        override fun toString(): String {
            return "Move[$position, $moveType]"
        }
    }
}