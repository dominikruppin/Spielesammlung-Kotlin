import classes.Player
import gameclasses.WWM
import kotlin.system.exitProcess
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import java.io.File

fun newPlayer(): Player {
    println("Gib bitte deinen Namen ein:")
    var name = readln()
    while (!name.any { char -> char.isLetter() }) {
        println("Ungültige Eingabe. Gib nur Buchstaben ein. Probiere es erneut:")
        name = readln()
    }
    println("Bitte gib nun dein Alter ein:")
    var age = readln()
    while (!age.any { char -> char.isDigit() }) {
        println("Ungültige Eingabe. Gib nur Zahlen ein. Probiere es erneut:")
        age = readln()
    }
    return Player(name, age.toInt())
}

fun hauptMenue(player: Player) {
    println("""Hallo ${player.name}! Was möchtest du heute spielen?
    |1) Wer wird Millionär (Einzelspieler)
    |2) Stimmts oder nicht? (Einzelspieler + Mehrspieler)
    |3) Wordmix (Einzelspieler + Mehrspieler""".trimMargin()
    )

    while (true) {
        when (readln()) {
            "1" -> {
                println("Du hast \"Wer wird Millionär\" ausgewählt.")
                loadingGame()
                var wwm = WWM()
                wwm.startGame(player)
                break
            }

            "2" -> {
                println("Du hast \"Stimmts oder nicht\" ausgewählt.")
                // StimmtsOderNicht()
                break
            }

            "3" -> {
                println("Du hast \"Wordmix\" ausgewählt.")
                //Wordmix()
                break
            }
            "4", "stop", "abbruch", "cancel" -> exitProcess(0)
            else -> println("Ungültige Eingabe, probiere es erneut:")
        }
    }
}

fun loadingGame() {
    print("Das Spiel wird gestartet.")
    repeat(5) {
        Thread.sleep(500)
        print(".")
    }
    clearConsole()
}

fun clearConsole() {
    println("\n".repeat(100))
}

fun playSound(soundFile: String) {
    try {
        val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(File(soundFile))
        val clip: Clip = AudioSystem.getClip()
        clip.open(audioInputStream)
        clip.start()
        // Der Sound wird asynchron abgespielt, daher warten wir hier, bis er beendet ist
        Thread.sleep(clip.microsecondLength / 1000) // In Millisekunden umwandeln
    } catch (ex: Exception) {
        println("Fehler beim Abspielen der Audiodatei: $ex")
    }
}