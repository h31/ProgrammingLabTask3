package logic.russian

import logic.Player
import logic.Side

class RussianPlayer(override val side: Side, override val name: String) : Player {
    constructor(side: Side) : this(side, "unknown")

    override var lostCheckers: Int = 0
}