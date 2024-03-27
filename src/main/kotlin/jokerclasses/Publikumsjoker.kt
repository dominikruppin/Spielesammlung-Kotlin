package jokerclasses

import classes.Player
import gameclasses.WWM
import questionclasses.MultipleChoiceQuestion
import kotlin.random.Random

/**
 * Die Klasse `Publikumsjoker` implementiert die Verwendung des Publikumsjokers beim Spiel Wer wird Millionär.
 * Dieser Joker ermöglicht es dem Spieler, das Publikum um Hilfe bei einer Frage zu bitten, indem
 * die Prozentsätze der abgegebenen Antworten angezeigt werden.
 * Dabei wird mit Wahrscheinlichkeiten gearbeitet, je nach Schweregrad der Frage.
 *
 * @property name Der Name des Jokers, standardmäßig auf "Publikumsjoker" gesetzt.
 */
class Publikumsjoker(name: String = "Publikumsjoker"): Joker(name) {

    /**
     * Die Methode `use` wird aufgerufen, um den Publikumsjoker zu verwenden.
     *
     * @param wwm Die Instanz des Wer wird Millionär-Spiels.
     * @param player Der aktuelle Spieler als Objekt.
     * @param question Das Objekt der MultipleChoice-Frage, für die der Joker verwendet wird.
     */
    fun use(player: Player, wwm: WWM, question: MultipleChoiceQuestion) {
        println("\nDu hast den Publikumsjoker ausgewählt.\n")
        Thread.sleep(3000)
        println("Liebes Publikum, nehmen Sie nun die Fernbedienung und stimmen Sie ab...")
        Thread.sleep(10000)
        println("Das Publikum hat folgendermaßen abgestimmt:")

        // Generiere den Prozentsatz basierend auf der Schwierigkeit der Frage
        val correctPercentage = percentageRange(question.difficulty)
        // Generiere die Ergebnisse für die richtige und falsche Antworten und gib sie sortiert (a nach d) aus
        val results = generateCorrectResult(question.answer, correctPercentage)
        results.toSortedMap().forEach { (answer, percentage) ->
            println("$answer: $percentage%")
        }
        Thread.sleep(6000)
        println("Hier zur Übersicht noch einmal die Frage:")
        question.getQuestion(player, wwm)
        println("Bitte gib nun deine Antwort ein:")
    }

    /**
     * Die Methode `percentageRange` gibt einen zufälligen Prozentsatz basierend auf der
     * Schwierigkeit der Frage zurück.
     *
     * @param difficulty Die Schwierigkeit der Frage.
     * @return Ein zufälliger Prozentsatz im angegebenen Bereich.
     */
    private fun percentageRange(difficulty: String) = when (difficulty) {
        "easy" -> Random.nextInt(60, 81)
        "medium" -> Random.nextInt(30, 51)
        "strong" -> Random.nextInt(10, 41)
        else -> 50
    }

    /**
     * Die Methode `generateCorrectResult` generiert die Ergebnisse für die richtige Antwort.
     *
     * @param answerIndex Der Index der richtigen Antwort.
     * @param correctPercentage Der Prozentsatz für die richtige Antwort, der von der Methode `percentageRange` abgerufen wird.
     * @return Eine Map, die die Ergebnisse für die richtige Antwort enthält. (Key = Char, Value = %)
     */
    private fun generateCorrectResult(answerIndex: Int, correctPercentage: Int): Map<Char, Int> {
        val correctAnswerAsChar = 'a' + answerIndex
        val remainingAnswers = ('a'..'d').filter { char -> char != correctAnswerAsChar }.toMutableList()

        val results = mutableMapOf(correctAnswerAsChar to correctPercentage)
        generateFalseResults(remainingAnswers, 100 - correctPercentage, results)

        return results
    }

    /**
     * Die Methode `generateFalseResults` generiert die Ergebnisse für die falschen Antworten.
     *
     * @param remainingAnswers Eine Liste der verbleibenden falschen Antworten.
     * @param remainingPercentage Der verbleibende Prozentsatz für die falschen Antworten.
     * @param results Eine Map, in der die Ergebnisse gespeichert werden.
     */
    private fun generateFalseResults(remainingAnswers: MutableList<Char>, remainingPercentage: Int, results: MutableMap<Char, Int>) {
        var remainingPercentageLeft = remainingPercentage

        // Für den Fall, dass nur noch eine Antwort übrig ist, erhält sie den gesamten verbleibenden Prozentsatz
        if (remainingAnswers.size == 1) {
            results[remainingAnswers.first()] = remainingPercentageLeft
            return
        }

        // Iteriere über die verbleibenden Antworten außer der letzten
        remainingAnswers.dropLast(1).forEachIndexed { index, answer ->
            // Wir weisen den Antworten nun dynamische % Zahlen zu.
            // Der maximale Wert liegt jetzt bei remainingPercentageLeft / 2.
            // Also bei 50% von den verbleibenden Prozentpunkten
            val percentage = Random.nextInt(1, remainingPercentageLeft / 2)
            results[answer] = percentage
            remainingPercentageLeft -= percentage
        }

        // Die letzte Antwort erhält den verbleibenden Prozentsatz, um sicherzustellen, dass die Summe nicht über 100% liegt
        results[remainingAnswers.last()] = remainingPercentageLeft
    }
}