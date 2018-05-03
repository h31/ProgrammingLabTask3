package logic

interface Player {
    val side: Side
    val name: String
    var lostCheckers: Int
}