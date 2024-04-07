package jokerclasses

import classes.Names
import classes.Player
import gameclasses.WWM
import questionclasses.MultipleChoiceQuestion

class Telefonjoker(name: String = "Telefonjoker"): Joker(name) {
    private val names = Names()


    fun use(wwm: WWM, player: Player, question: MultipleChoiceQuestion): Boolean {
        println("Du hast den Telefonjoker ausgewählt.")
        // Wir erstellen eine Map, Key ist der Name der Person, Int der Index der Antwort die die Person gibt
        val people = mutableMapOf<String, Int>()
        // Wir haben eine Liste mit allen möglichen Antworten (Index 0 bis 3, 0 bedeutet a, 1 b, 2 c, 3 d)
        val falseAnswers = mutableListOf(0, 1, 2, 3)
        // Wir entfernen die richtige Antwort aus der Liste der falschen Antworten (falseAnswers)
        falseAnswers.remove(question.answer)
        // Wir speichern wie viele Personen die richtige Antwort haben, initalisieren mit 0
        var howMuchPeopleAreRight = 0

        // Basierend auf der Schwierigkeit der Frage wird anhand der Int-Range ermittelt, wie viele der 3 möglichen
        // Personen die man anrufen kann, die richtige Antwort kennen. Den Wert speichern wir als Int in howMuchPeopleAreRight
        when (question.difficulty) {
            "easy" -> howMuchPeopleAreRight = (2..3).random()
            "medium" -> howMuchPeopleAreRight = (1..2).random()
            "strong" -> howMuchPeopleAreRight = (0..1).random()
        }

        println()

        // Wir prüfen ob die Anzahl der in der Map gespeicherten Personen niedriger ist als die Anzahl der Personen, die die richtige Antwort kennen
        while (people.size < howMuchPeopleAreRight) {
            // Wir holen uns einen zufällig generierten Namen
            val name = names.generateRandomName()
            // Wir speichern in der Map people anhand des Namens (keys) die richtige Antwort (value) ab
            people[name] = question.answer
        }

        // Wenn die Map people weniger als 3 Personen (Einträge) beinhaltet, fügen wir weitere hinzu.
        while (people.size < 3) {
            // Wir holen uns wieder einen zufällig generierten Namen
            val name = names.generateRandomName()
            // Wenn es den Namen schon in der Map gibt, springen wir wieder zum Schleifenbeginn. Grund: Jeder Name ist ein Key und darf daher nur 1x vorkommen-
            // Im schlimmsten Fall überschreiben wir also eine Person, die die richtige Antwort kannte
            if (people.contains(name)) {
                continue
            }
            // Wir speichern wieder anhand des Namens (keys) und diesmal einer falschen Antwort (value) in der Map people
            people[name] = falseAnswers.random()
        }

        println("Du kannst folgende Personen anrufen:")
        // Wir zählen mit, die wie vielte Person es ist
        var index = 1
        // Wir holen uns nur die Namen (keys) der Personen in der Map people
        val peopleWithIndex = people.keys.toList()
        // Wir itieren über die Namen (keys) der Personen
        for (person in peopleWithIndex) {
            println("$index) $person")
            index++
        }
        println("\nWen möchtest du davon anrufen?")
        var input: Int?
        // Wer wird angerufen?
        var calling: String = ""
        while (true) {
            input = readln().toIntOrNull()
            when (input) {
                null -> println("Ungültige Eingabe. Nutze bitte die Ziffern vor den Namen.")
                !in 1 until index -> println("Es gibt niemanden mit der Ziffer.")
                else -> {
                    // Wir holen uns anhand der eingegeben Ziffer (-1, um den korrekten Index zu haben) den Namen der Person die angerufen wird
                    calling = peopleWithIndex[input - 1]
                    println("Du hast $calling ausgewählt.")
                    break
                }
            }
        }

        val bold = "\u001B[1m"
        val reset = "\u001B[0m"
        println("\nDann rufen wir mal bei $calling an...\n")
        Thread.sleep(2000)
        println("Düdüdüüüü, Dü-dü-dü-dü, Dü-düüü-düüü\n")
        Thread.sleep(4000)
        println("${bold}Günther Jauch:${reset} Hallo, hier ist Günther Jauch. Spreche ich mit $calling?")
        Thread.sleep(3000)
        println("${bold}$calling:${reset} Ja, hallo, hier ist $calling!")
        Thread.sleep(3000)
        println("""
            |${bold}Günther Jauch:${reset} Guten Abend, $calling. Bei uns sitzt heute ${player.name}.
            |Wir sind bei der ${wwm.round+1}ten Frage, wir benötigen Ihre Hilfe. 
            |Ich gebe nun ab an ${player.name}
        """.trimMargin())
        Thread.sleep(5000)
        println("""
            |${bold}Du:${reset} Hey, $calling, ich hoffe es geht dir gut.
            |Pass auf, ich brauche deine Hilfe bei folgender Frage:
            |${question.question}
        """.trimMargin())
        Thread.sleep(5000)
        println("${bold}$calling:${reset} Puh, lass mich kurz überlegen.")
        Thread.sleep(6000)
        println("${bold}$calling:${reset} Ich bin mir sicher, dass es ${'a' + people[calling]!!} ist.\n")
        Thread.sleep(4000)
        println("${bold}Günther Jauch:${reset} Hoffen wir, dass der Telefonjoker weiter geholfen hat.\nHier noch einmal die Frage:\n")
        question.getQuestion(player, wwm)
        println("\nBitte gib nun deine Antwort ab:")
        return false
    }

}