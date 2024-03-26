package jokerclasses

import questionclasses.MultipleChoiceQuestion

class FiftyFiftyJoker(name: String = "50/50"): Joker(name) {
     fun use(question: MultipleChoiceQuestion) {
        println("Du hast den 50/50 Joker ausgewählt.\nWir entfernen nun zwei falsche Antworten...")
        val falseAnswers = (0 until question.options.size).filter { index -> index != question.answer }.shuffled().take(2)
        println(question.question)
        question.options.forEachIndexed { index, option ->
            val char = 'a' + index
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

        // EINLOGGEN:
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
     }
}