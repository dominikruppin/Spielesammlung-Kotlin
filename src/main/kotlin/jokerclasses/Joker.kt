package jokerclasses

import classes.Player
import gameclasses.WWM
import questionclasses.MultipleChoiceQuestion

open class Joker(val name: String = "Joker") {
    fun getJoker(player: Player, wwm: WWM, question: MultipleChoiceQuestion): Boolean {
        println("Du hast noch folgende Joker zur Verfügung:")
        wwm.joker.forEachIndexed { index, joker ->
            println("${index + 1}) ${joker.name}")
        }
        println("${wwm.joker.size+1}) Doch keinen Joker wählen\n")
        println("Wähle nun deinen Joker durch Eingabe der Zahl aus:")
        var input: Int?
        while (true) {
            input = readln().toIntOrNull()
            when {
                input == null -> println("Ungültige Eingabe. Bitte nur die entsprechende Zahl eingeben. Probiere es erneut.")
                input == wwm.joker.size+1 -> {
                    println("Du versuchst es doch alleine? Ich drücke dir die Daumen!")
                    println("Beantworte nun die bereits gestellte Frage:")
                    question.getQuestion(player, wwm)
                    return false
                }
                !wwm.joker.indices.contains(input-1) -> println("Den Joker mit der Auswahl ${input} gibt es nicht.")
                else -> break
            }
        }

        when (wwm.joker[input!! - 1]) {
            is FiftyFiftyJoker -> {
                val fiftyFiftyJoker = wwm.joker[input - 1] as FiftyFiftyJoker
                fiftyFiftyJoker.use(question)
                wwm.joker.remove(fiftyFiftyJoker)
                return true
            }
            is Telefonjoker -> {
                val telefonjoker = wwm.joker[input -1] as Telefonjoker
                telefonjoker.use(wwm, player, question)
                wwm.joker.remove(telefonjoker)
            }
            is Publikumsjoker -> {
                val publikumsjoker = wwm.joker[input -1] as Publikumsjoker
                publikumsjoker.use(player, wwm, question)
                wwm.joker.remove(publikumsjoker)
            }
        }
        return false
    }
}