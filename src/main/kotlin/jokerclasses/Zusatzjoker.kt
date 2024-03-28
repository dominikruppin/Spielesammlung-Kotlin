package jokerclasses

import classes.Names
import classes.Player
import gameclasses.WWM
import questionclasses.MultipleChoiceQuestion
import kotlin.system.exitProcess

class Zusatzjoker(name: String = "Zusatzjoker"): Joker(name) {
    private val names = Names()

    fun use(wwm: WWM, player: Player, question: MultipleChoiceQuestion) {
        var howMuchPeopleStandup = 0
        val randomizer = mutableListOf<Boolean>()
        val peopleWithAnswer = mutableMapOf<String, Boolean>()
        // Bei Easy sollen 5 bis 8 Leute aufstehen, 90% Chance auf die richtige Antwort
        // Bei Medium sollen 4 bis 6Leute aufstehen, 80% Chance auf die richtige Antwort
        // Bei Strong sollen 0 bis 3 Leute aufstehen, 70% Chance auf die richtige Antwort
        println("Du hast den Zusatzjoker ausgewählt.")
        Thread.sleep(5000)
        println("\nLiebes Publikum, wenn Sie die Antwort auf die Frage \"${question.question}\" kennen, stehen Sie bitte jetzt auf.\n")
        when (question.difficulty) {
            "easy" -> {
                howMuchPeopleStandup = (5..7).random()
                randomizer.addAll(listOf(true, true, true, true, true, true, true, true, true, false))
            }

            "medium" -> {
                howMuchPeopleStandup = (3..5).random()
                randomizer.addAll(listOf(true, true, true, false, true, true, true, true, true, false))
            }

            "strong" -> {
                howMuchPeopleStandup = (0..3).random()
                randomizer.addAll(listOf(true, true, false, true, true, false, true, true, true, false))
            }
        }

        while (peopleWithAnswer.size < howMuchPeopleStandup) {
            peopleWithAnswer[names.generateRandomName()] = randomizer.random()
        }

        Thread.sleep(5000)
        if (howMuchPeopleStandup > 0) {
            for (people in peopleWithAnswer) {
                println("${people.key} steht auf...")
                Thread.sleep((2000..4000).random().toLong())
            }

            println("\nFolgende Personen sind aufgestanden:")
            var index = 1
            val peoplesWithIndex = peopleWithAnswer.keys.toList()
            for (person in peoplesWithIndex) {
                println("$index) $person")
                index++
            }

            println()
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
                            choosedPeople = peoplesWithIndex[input - 1]
                            println("Du hast $choosedPeople ausgewählt.")
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
            var answer = 'a' + question.answer
            var allOptions = mutableListOf('a', 'b', 'c', 'd')
            allOptions.remove(answer)
            if (peopleWithAnswer[choosedPeople]!!) {
                println("... $answer ist!")
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