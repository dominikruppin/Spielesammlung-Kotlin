package jokerclasses

import classes.Names
import classes.Player
import gameclasses.WWM
import questionclasses.MultipleChoiceQuestion
import kotlin.system.exitProcess

/**
 * Repräsentiert den Zusatzjoker.
 * Beinhaltet die Funktion use, um den Zusatzjoker zu benutzen.
 */
class Zusatzjoker(name: String = "Zusatzjoker"): Joker(name) {
    private val names = Names()


    fun use(wwm: WWM, player: Player, question: MultipleChoiceQuestion) {
        // Wir speichern hier, wie viele Personen aufstehen, als Integer
        var howMuchPeopleStandup = 0
        // Eine veränderliche Liste aus Booleans, aus der wir nachher zufällig entscheiden ob jemand die richtige Antwort kennt
        val randomizer = mutableListOf<Boolean>()
        // Eine Map, in der wir die Namen der Personen und einen Boolean speichern, ob die Person die richtige Antwort kennt
        val peopleWithAnswer = mutableMapOf<String, Boolean>()
        println("Du hast den Zusatzjoker ausgewählt.")
        Thread.sleep(5000)
        println("\nLiebes Publikum, wenn Sie die Antwort auf die Frage \"${question.question}\" kennen, stehen Sie bitte jetzt auf.\n")
        // Abhängig vom Schwierigkeitsgrad der Frage entscheidet sich wie viele Leute aufstehen und wie die Chancen auf die richtig Antwort ist
        when (question.difficulty) {
            // Bei Easy sollen 5 bis 7 Leute aufstehen, 90% Chance auf die richtige Antwort
            "easy" -> {
                howMuchPeopleStandup = (5..7).random()
                randomizer.addAll(listOf(true, true, true, true, true, true, true, true, true, false))
            }
            // Bei Medium sollen 3 bis 5 Leute aufstehen, 80% Chance auf die richtige Antwort
            "medium" -> {
                howMuchPeopleStandup = (3..5).random()
                randomizer.addAll(listOf(true, true, true, false, true, true, true, true, true, false))
            }
            // Bei Strong sollen 0 bis 3 Leute aufstehen, 70% Chance auf die richtige Antwort
            "strong" -> {
                howMuchPeopleStandup = (0..3).random()
                randomizer.addAll(listOf(true, true, false, true, true, false, true, true, true, false))
            }
        }

        // Wir fügen so viele Personen der Map hinzu, bis die Anzahl der Personen aus howMuchPeopleStandup erreicht ist
        while (peopleWithAnswer.size < howMuchPeopleStandup) {
            // Wir holen uns einen zufälligen Namen
            val name = names.generateRandomName()
            // Wenn der zufällige Name bereits in der Map vorhanden ist, überspringen wir den weiteren Code und führen die Schleife erneut aus
            // So verhindern wir ein überschreiben, da jeder Key (Name) unique ist
            if (peopleWithAnswer.keys.contains(name)) {
                continue
            }
            // Wir speichern anhand des Namens (key) den value (boolean, zufällig aus der Liste gezogen) die Person in der Map peopleWithAnswer
            peopleWithAnswer[name] = randomizer.random()
        }

        Thread.sleep(5000)
        // Wenn mehr als eine Person oben generiert wurde, die aufsteht...
        if (howMuchPeopleStandup > 0) {
            // Wir itieren über die Liste der Personen die aufgestanden sind, immer mit einer zufälligen Zeitverzögerung zwischen 2 und 4 Sekunden
            for (people in peopleWithAnswer) {
                println("${people.key} steht auf...")
                Thread.sleep((2000..4000).random().toLong())
            }

            println("\nFolgende Personen sind aufgestanden:")
            var index = 1
            val peoplesWithIndex = peopleWithAnswer.keys.toList()
            // Wir itieren noch einmal über alle aufgestanden Personen, geben den index+1 (somit starten wir bei 1, statt bei 0) und deren Namen aus
            for (person in peoplesWithIndex) {
                println("$index) $person")
                index++
            }

            println()
            // Der Name der ausgewählten Person als String, anfangs als leerer String initialisiert
            var choosedPeople: String = ""
            if (howMuchPeopleStandup > 1) {
                println("\nWen möchtest du auswählen?")
                var input: Int?
                while (true) {
                    input = readln().toIntOrNull()
                    when (input) {
                        null -> println("Ungültige Eingabe. Nutze bitte die Ziffern vor den Namen.")
                        !in 1 until index -> println("Es gibt niemanden mit der Ziffer.")
                        else -> {
                            // Hier wieder von der Eingabe -1 rechnen, da wir oben +1 gerechnet haben, damit wir bei 1 anfangen, statt bei 0
                            // Wir holen uns anhand des Indexes (Eingabe -1) dann den Namen der Person und speichern ihn in choosedPeople
                            choosedPeople = peoplesWithIndex[input - 1]
                            println("Du hast $choosedPeople ausgewählt.")
                            // Wir haben nun eine gültige Eingabe, also unterbrechen wir die schleife
                            break
                        }
                    }
                }
            } else {
                println("Da leider nur eine Person aufgestanden bist, hast du keine andere Wahl.")
                choosedPeople = peopleWithAnswer.keys.first()
            }

            Thread.sleep(4000)
            println("Wir überreichen $choosedPeople nun ein Mikrofon.")
            Thread.sleep(5000)
            println("Guten Tag, ich bin $choosedPeople. Ich denke, dass es...")
            Thread.sleep(7000)
            // answer beinhaltet einen Char und stellt die Auswahl als Buchstaben da. question.answer ist der Index der richtigen Antwort.
            // Wir holen uns bei answer also die richtige Antwort als char
            var answer = 'a' + question.answer
            // Wir haben eine Liste mit allen möglichen Antworten
            var allOptions = mutableListOf('a', 'b', 'c', 'd')
            // Aus der Liste entfernen wir die richtige Option (in answer haben wir uns dazu schon den passenden Char geholt)
            allOptions.remove(answer)
            // Wenn die Person als value true (boolean) hat, kennt sie die richtige Antwort und gibt diese auch aus
            if (peopleWithAnswer[choosedPeople]!!) {
                println("... $answer ist!")
                // anderenfalls kennt sie nicht die richtige Antwort und gibt zufällig eine der falschen Möglichkeiten aus
            } else {
                println("... ${allOptions.random()} ist.")
            }
        } else {
            println("Es ist leider niemand aufgestanden...")
            println("Das tut uns sehr leid. Der Joker wurde leider trotzdem verbraucht.")
        }

        println("\nHier zur Erinnerung noch einmal die Frage:\n")
        Thread.sleep(3000)
        question.getQuestion(player, wwm)
        println("Bitte gib deine Antwort ein:")
    }
}