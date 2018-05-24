package view.tornadofx

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.StrokeType
import logic.Board
import logic.MoveType
import logic.Side
import logic.russian.RussianCheckers
import tornadofx.*


class Board : View() {
    val checkers = RussianCheckers()
    var selectedCell: StackPane? = null
    var selectedRectangle: Rectangle? = null
    var moves: Set<Board.Move> = setOf()
    var grid: GridPane? = null

    override val root = stackpane {
        alignment = Pos.CENTER

        grid = gridpane {
            maxHeightProperty().bind(this@stackpane.widthProperty())
            maxWidthProperty().bind(this@stackpane.heightProperty())

            prefHeightProperty().bind(this@stackpane.heightProperty())
            prefWidthProperty().bind(this@stackpane.widthProperty())

            for (row in 0..7) {
                row {
                    // 1/8%
                    for (col in 0..7) {
                        val color = if (row % 2 == 1 && col % 2 == 1
                                || row % 2 == 0 && col % 2 == 0) Color.WHITE
                        else Color.BLACK

                        stackpane {
                            fitToParentSize()

                            onMouseClicked = EventHandler {
                                selectCell(row, col)
                            }

                            rectangle {
                                heightProperty().bind(this@stackpane.heightProperty())
                                widthProperty().bind(this@stackpane.widthProperty())

                                fill = color
                                stroke = Color.GREEN
                                strokeType = StrokeType.INSIDE
                                strokeWidth = 0.0
                            }

                            imageview {
                                fitHeightProperty().bind(this@stackpane.heightProperty().multiply(0.8))
                                fitWidthProperty().bind(this@stackpane.widthProperty().multiply(0.8))

                                if (((row == 0 || row == 2) && col % 2 == 1) || row == 1 && col % 2 == 0)
                                    image = resources.image("/black.png")
                                else if (((row == 5 || row == 7) && col % 2 == 0) || row == 6 && col % 2 == 1)
                                    image = resources.image("/white.png")
                            }
                        }
                    }
                }
            }

            for (i in 0..7) {
                constraintsForRow(i).percentHeight = 12.5
                constraintsForColumn(i).percentWidth = 12.5
            }
        }
    }

    fun selectCell(row: Int, col: Int) {

        for (move in moves) {
            val cell = getNodeByRowColumnIndex(move.position.y - 1, move.position.x - 1) as StackPane

            for (node in cell.children) {
                if (node is Rectangle) {
                    node.strokeWidth = 0.0
                }
            }
        }

        for (move in moves) {
            if (move.position == Board.Position(col + 1, row + 1)) {
                moveChecker(move)
                return
            }
        }

        moves = checkers.selectChecker(col + 1, row + 1)

        if (!moves.isEmpty()) {
            selectedRectangle?.strokeWidth = 0.0
            selectedCell = getNodeByRowColumnIndex(row, col) as StackPane

            for (node in selectedCell!!.children) {
                if (node is Rectangle) {
                    node.stroke = Color.GREEN
                    node.strokeWidth = 5.0
                    selectedRectangle = node
                }
            }

            for (move in moves) {
                val cell = getNodeByRowColumnIndex(move.position.y - 1, move.position.x - 1) as StackPane

                for (node in cell.children) {
                    if (node is Rectangle) {
                        node.stroke = Color.GRAY
                        node.strokeWidth = 5.0
                    }
                }
            }

        } else {
            selectedRectangle?.strokeWidth = 0.0
            selectedRectangle = null
            selectedCell = null
            moves = setOf()
        }
    }

    fun moveChecker(move: Board.Move) {
        val nextCell = getNodeByRowColumnIndex(move.position.y - 1, move.position.x - 1) as StackPane
        val capturedPosition = checkers.moveChecker(move)

        for (node in selectedCell!!.children) {
            if (node is ImageView) {
                node.image = null
            }
        }

        for (node in nextCell.children) {
            if (node is ImageView) {
                val checker = checkers.board.checkChecker(move.position)!!
                node.image = if (checker.side == Side.WHITE) if (checker.isKing) resources.image("/white_king.png")
                else resources.image("/white.png")
                else if (checker.isKing) resources.image("/black_king.png")
                else resources.image("/black.png")
            }
        }


        if (move.moveType == MoveType.CAPTURE) {
            val capturedCell = getNodeByRowColumnIndex(capturedPosition!!.y - 1, capturedPosition.x - 1) as StackPane
            for (node in capturedCell.children) {
                if (node is ImageView) {
                    node.image = null
                }
            }
        }

        selectedRectangle?.strokeWidth = 0.0
        selectedRectangle = null
        selectedCell = null
        moves = setOf()
    }

    fun getNodeByRowColumnIndex(row: Int, column: Int): Node? {
        var result: Node? = null
        val childrens = grid!!.children

        for (node in childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node
                break
            }
        }

        return result
    }
}