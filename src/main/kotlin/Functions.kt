import classes.Player
import gameclasses.WWM
import gameclasses.Wordmix
import kotlin.system.exitProcess
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

/**
 * Erstellt einen neuen Spieler, indem der Benutzer nach seinem Namen und Alter gefragt wird.
 *
 * @return Ein neues Spielerobjekt, das den Namen und das Alter des Benutzers enthält.
 */
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

    if (age.toInt() < 12) {
        println("Leider darfst du erst ab 12 Jahren spielen.\nDas Spiel wird nun beendet.")
        exitProcess(0)
    }

    return Player(name, age.toInt())
}

fun hauptMenue(player: Player) {
    println("""Hallo ${player.name}! Was möchtest du heute spielen?
    |1) Wer wird Millionär (Einzelspieler + Mehrspieler)
    |2) Wordmix (Einzelspieler)""".trimMargin()
    )

    while (true) {
        when (readln()) {
            "1" -> {
                println("Du hast \"Wer wird Millionär\" ausgewählt.")
                loadingGame()
                val wwm = WWM()
                wwm.startGame(player)
                break
            }

            "2" -> {
                println("Du hast \"Wordmix\" ausgewählt.")
                Wordmix.use(player)
                break
            }
            "stop", "abbruch", "cancel" -> exitProcess(0)
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

/**
 * Funktion zum scheinbaren clearen der Konsole.
 * Es werden 50.000 Zeilen mit der Nachricht "Nachgucken ist nicht erlaubt mein Freund" eingefügt,
 * so werden alte Ausgaben weggespammt.
 * Anschließend werden 500 Leerzeilen für die Optik eingefügt, damit man die Spamnachrichten nicht sieht.
 */
fun clearConsole() {
    println("Nachgucken ist nicht erlaubt mein Freund!".repeat(50000))
    println("\n".repeat(500))
}

/**
 * Funktion zum Abspielen einer Sounddatei.
 *
 * @param soundFile Der Dateiname der Sounddatei, die abgespielt werden soll.
 * @return Ein Clip-Objekt, das für die Wiedergabe des Sounds verwendet wird.
 */
fun playSound(soundFile: String): Clip {
        // Wir holen uns den Pfad zur übergebenen Datei (soundFile)
        val resource = object {}.javaClass.getResourceAsStream("/$soundFile")
        // Prüfen ob der Pfad gefunden wurde
        if (resource == null) {
            println("Die Datei $soundFile wurde nicht gefunden.")
        }

        // Es wird aus dem übergebenen Dateipfad ein AudioInputStream erstellt
        val audioInputStream: AudioInputStream = AudioSystem.getAudioInputStream(resource)
        // Hier wird ein neuer Clip erstellt. Ein Clip ist ein abspielbares Element für Audiodaten
        val clip: Clip = AudioSystem.getClip()
        // Diese Zeile öffnet den Clip und gibt ihm den vorher erstellten AudioInputStream
        clip.open(audioInputStream)
        // Der Clip (also die importierte Audiodatei) wird nun abgespielt.
        clip.start()

        return clip
}

/**
 * Funktion zur Formatierung von Millisekunden in das Format MM:SS.mmm (Minuten:Sekunden.Millisekunden).
 *
 * @param time Die Zeit in Millisekunden, die formatiert werden soll.
 * @return Ein String im Format MM:SS.mmm
 */
fun formatMiliseconds(time: Long): String {
    val minutes = (time / 60000).toInt()
    val seconds = ((time % 60000) / 1000).toInt()
    val milliseconds = (time % 1000).toInt()

    return "%02d:%02d.%03d".format(minutes, seconds, milliseconds)
}