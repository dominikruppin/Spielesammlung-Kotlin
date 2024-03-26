import kotlin.system.exitProcess

fun main() {
    val player = newPlayer()
    if (player.age < 12) {
        println("Leider darfst du erst ab 12 Jahren spielen.\nDas Spiel wird nun beendet.")
        exitProcess(0)
    }
    hauptMenue(player)
}