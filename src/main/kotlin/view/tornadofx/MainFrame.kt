package view.tornadofx

import tornadofx.*

class MainFrame : View() {
    val board = Board()
    override val root = borderpane {
        top = menubar {
            menu("Game") {
                item("New Game") {
                    setOnAction { board.newBoard() }
                }
            }
        }

        center = board.root
    }

    init {
        primaryStage.minWidth = 720.0
        primaryStage.minHeight = 780.0

        primaryStage.width = 800.0
        primaryStage.height = 860.0
    }
}