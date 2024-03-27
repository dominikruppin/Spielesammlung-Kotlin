package jokerclasses

import classes.Names
import classes.Player
import gameclasses.WWM
import questionclasses.MultipleChoiceQuestion

class Zusatzjoker(name: String = "Zusatzjoker"): Joker(name) {
    private val names = Names()

        fun use(wwm: WWM, player: Player, question: MultipleChoiceQuestion): Boolean {

            // Bei Easy sollen 5 bis 8 Leute aufstehen, 90% davon haben die richtige Antwort
            // Bei Medium sollen 4 bis 6Leute aufstehen, 80% davon haben die richtige Antwort
            // Bei Strong sollen 1 bis 3 Leute aufstehen, 70% davon haben die richtige Antwort
            println("Du hast den Publikumsjoker ausgewählt.")
            val people = mutableMapOf<String, Int>()
            val falseAnswers = mutableListOf(0, 1, 2, 3)
            falseAnswers.remove(question.answer)
            var howMuchPeopleAreRight = 0

            when (question.difficulty) {
                "easy" -> howMuchPeopleAreRight = (2..3).random()
                "medium" -> howMuchPeopleAreRight = (1..2).random()
                "strong" -> howMuchPeopleAreRight = (0..1).random()
            }
            println()
            do {
                val name = names.generateRandomName()
                people[name] = question.answer
            } while (people.size < howMuchPeopleAreRight)

            while (people.size < 3) {
                val name = names.generateRandomName()
                if (people.contains(name)) {
                    continue
                }
                people[name] = falseAnswers.random()
            }

            println("Du kannst zwischen folgenden Personen wählen:")
            var index = 1
            val peopleWithIndex = people.keys.toList()
            for (person in peopleWithIndex) {
                println("$index) $person")
                index++
            }
            println("\nWen möchtest du davon anrufen?")
            var input: Int?
            var calling: String = ""
            while (true) {
                input = readln().toIntOrNull()
                when (input) {
                    null -> println("Ungültige Eingabe. Nutze bitte die Ziffern vor den Namen.")
                    !in 1 until index -> println("Es gibt niemanden mit der Ziffer.")
                    else -> {
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


