package logic

interface Checkers {
    val board: Board
    val playerWhite: Player
    val playerBlack: Player
    var turn: Side
    var selectedChecker: Board.Position?
    var needCapture: Boolean

    fun selectChecker(position: Board.Position)
    fun moveChecker(position: Board.Position)

    fun changeTurn() {
        turn = if (turn == Side.WHITE) Side.BLACK
        else Side.WHITE
        selectedChecker = null
        needCapture = board.isCaptureNeed(turn)
    }

    fun checkWinner()
}