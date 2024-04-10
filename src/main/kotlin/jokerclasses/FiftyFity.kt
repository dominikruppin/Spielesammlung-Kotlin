package jokerclasses

import classes.Player
import gameclasses.WWM
import hauptMenue
import questionclasses.MultipleChoiceQuestion

/**
 * Repräsentiert den 50/50 Joker.
 * Beinhaltet die use Funktion, um den Joker zu nutzen
 */
class FiftyFiftyJoker(name: String = "50/50"): Joker(name) {
    /**
     * Diese Funktion implementiert den 50/50 Joker für eine Multiple-Choice-Frage.
     * Sie entfernt zwei falsche Antworten zufällig und ermöglicht dem Benutzer, eine Antwort auszuwählen.
     * Die gewählte Antwort wird markiert und nach einer kurzen Verzögerung werden die korrekten und falschen Antworten angezeigt.
     *
     * @param question Die MultipleChoiceQuestion, für die der Joker angewendet wird.
     */
    fun use(question: MultipleChoiceQuestion, player: Player, wwm: WWM) {
        println("Du hast den 50/50 Joker ausgewählt.\nWir entfernen nun zwei falsche Antworten...")
         // Wir erstellen mit falseAnswers eine neue Liste mit den Indexen von falschen Antworten.
         // Dazu nutzen wir den Index (0 until question.options.size)
         // Der Filter entfernt die richtige Antwort aus den Optionen, so bleiben nur die falschen übrig
         // Diese falschen Antworten werden gemischt und 2 davon genommen
         // Am Ende sind in falseAnswers somit von 2 falsche Antworten der Index gespeichert
        val falseAnswers = (0 until question.options.size).filter { index -> index != question.answer }.shuffled().take(2)
        println(question.question)
        question.options.forEachIndexed { index, option ->
            val char = 'a' + index
            // Wir prüfen ob der aktuelle index in falseAnswers gespeichert ist, falls ja printen wir einen leeren String (somit ist die Option quasi weg)
            if (index in falseAnswers) {
                print("")
            } else {
                println("$char) $option")
            }
        }
        println("Durch die Eingabe von \"stop\" kannst du jederzeit aufhören.")
        println("Bitte gib deine Lösung ein, du hast dafür maximal 60 Sekunden Zeit:")
        var chooseIndex = 0

        do {
            while (true) {
                when (readln().lowercase()) {
                    "a" -> {
                        chooseIndex = 0
                        break
                    }

                    "b" -> {
                        chooseIndex = 1
                        break
                    }

                    "c" -> {
                        chooseIndex = 2
                        break
                    }

                    "d" -> {
                        chooseIndex = 3
                        break
                    }

                    else -> println("Ungültige Eingabe")
                }
            }
            if (chooseIndex in falseAnswers) {
                println("Du kannst keine Option wählen, die der Joker entfernt hat.")
            }
        } while (chooseIndex in falseAnswers)

        // EINLOGGEN (Wir markieren die gewählte Option farblich in orange)
        println("\nDeine Antwort wurde eingeloggt...\n")
         println(question.question)
         question.options.forEachIndexed { index, option ->
             val char = 'a' + index
             val choosedOption = 'a' + chooseIndex
             if (index in falseAnswers) {
                 print("")
             } else if (char == choosedOption) {
                 println("\u001B[33m$char) $option\u001B[0m")
             } else {
                 println("$char) $option")
             }
         }
         Thread.sleep(6000)
         println("\nSchauen wir mal, ob das so stimmt...\n")
         Thread.sleep(6000)
         println(question.question)
         // Wir geben die Antworten aus und markieren dabei die richtige Antwort grün. Falls die falsche Antwort gewählt wurde, wechselt diese auf rot.
         question.options.forEachIndexed { index, option ->
             val char = 'a' + index
             val choosedOption = 'a' + chooseIndex
             if (index in falseAnswers) {
                 print("")
             } else if (char == choosedOption && index == question.answer) {
                 println("\u001B[32m$char) $option\u001B[0m")
             } else if (char == choosedOption && index != question.answer) {
                 println("\u001B[31m$char) $option\u001B[0m")
             } else if (index == question.answer) {
                 println("\u001B[32m$char) $option\u001B[0m")
             } else {
                 println("$char) $option")
             }
         }
        if (question.answer == chooseIndex) {
            if (wwm.round == 14) {
                println("!!!!!!!DU BIST MILLIONÄR!!!!!")
                Thread.sleep(5000)
                hauptMenue(player)
                return
            }
        }
     }
}