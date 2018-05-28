package view.tornadofx

import javafx.geometry.Pos
import logic.Side
import tornadofx.*

class WinnerWindow : Fragment() {
    val side: Side by param()
    val board: Board by param()

    override val root = vbox {
        alignment = Pos.CENTER

        label {
            text = side.toString() + " won!"
            vboxConstraints { marginBottom = 10.0 }
        }

        hbox {
            alignment = Pos.CENTER

            button {
                text = "New game"
                setOnAction {
                    board.newBoard()
                    close()
                }
                hboxConstraints { marginRight = 10.0 }
            }

            button {
                text = "Exit"
                setOnAction { System.exit(0) }
            }
        }
    }
}