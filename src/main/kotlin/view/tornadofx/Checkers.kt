package view.tornadofx

import javafx.application.Application
import tornadofx.App

class Checkers : App(Board::class) {

}

fun main(args: Array<String>) {
    Application.launch(Checkers::class.java, *args)
}
