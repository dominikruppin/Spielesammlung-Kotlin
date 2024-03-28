package gameclasses

import classes.Player
import clearConsole
import formatMiliseconds
import hauptMenue
import jokerclasses.*
import newPlayer
import playSound
import questionclasses.*

class WWM(name: String = "Wer wird Millionär"): Game(name) {
    val joker = mutableListOf<Joker>()
    var round = 0
    var risiko = false
    var players = 1
    private var easyQuestions: MutableList<MultipleChoiceQuestion>
    private var mediumQuestions: MutableList<MultipleChoiceQuestion>
    private var strongQuestions: MutableList<MultipleChoiceQuestion>

    // Wir trennen die Fragen zwischen den Schwierigkeitsgraden, so können wir sund mit random leichter eine zufällige holen
    init {
        this.joker.addAll(listOf(FiftyFiftyJoker(), Telefonjoker(), Publikumsjoker()))
        this.easyQuestions = multipleChoiceQuestions.filter { it.difficulty == "easy" }.toMutableList()
        this.mediumQuestions = multipleChoiceQuestions.filter { it.difficulty == "medium" }.toMutableList()
        this.strongQuestions = multipleChoiceQuestions.filter { it.difficulty == "strong" }.toMutableList()
    }

    /**
     * Erzeugt eine neue Frage. Die gewählte Schwierigkeit basiert auf der aktuellen Runde.
     *
     * @param round Die aktuelle Runde des Spiels.
     * @return Eine neue MultipleChoiceQuestion entsprechend der Runde.
     */
    private fun newQuestion(round: Int): MultipleChoiceQuestion {
        var question: MultipleChoiceQuestion
        when {
            round < 5 -> {
                question = easyQuestions.random()
                easyQuestions.remove(question)
            }
            round < 10 -> {
                question = mediumQuestions.random()
                mediumQuestions.remove(question)
            }
            else -> {
                question = strongQuestions.random()
                strongQuestions.remove(question)
            }
        }
        return question
    }

    /**
     * Fragt den Nutzer, ob er die Spielregeln für Wer wird Millionär lesen möchte oder nicht.
     * Falls ja, werden die Spielregeln ausgegeben und danach startet das Spiel, falls nein,
     * startet direkt das Spiel.
     */
    private fun gameRules() {
        println("Möchtest du die Spielregeln lesen?\n1) Ja\n2) Nein")
        while (true) {
            when (readln().lowercase()) {
                "1", "ja" -> {
                    println("Du hast ja ausgewählt. Wir erklären dir nun die Spielregeln...")
                    println("""
                        |"Wer wird Millionär" ist ein Quizspiel, bei dem Kandidaten Fragen aus verschiedenen Wissensbereichen beantworten müssen, um Geldpreise zu gewinnen. 
                        |Die Fragen werden in aufsteigender Schwierigkeit gestellt, wobei jeder Frage ein höherer Geldbetrag zugeordnet ist, bis hin zur Million. 
                        |Der Kandidat hat vier Joker, die ihm helfen können:
                        |
                        |- Publikumsjoker: Der Kandidat fragt das Publikum nach ihrer Meinung, welche Antwort richtig ist.
                        |
                        |- Telefonjoker: Der Kandidat darf eine Person seiner Wahl anrufen und um Hilfe bei der Beantwortung der Frage bitten.
                        |
                        |- 50:50-Joker: Zwei falsche Antworten werden eliminiert, sodass dem Kandidaten nur noch zwei Antwortmöglichkeiten bleiben.
                        |
                        |- Zusatzjoker: Du kannst die Absicherung bei 16.000€ gegen einen Joker tauschen. Es stehen Leute aus dem Publikum auf, welche 
                        |  denken, dass sie die Lösung kennen.
                        |
                        |Um einen Joker zu nutzen, gib statt der Lösung der Frage einfach das Wort "Joker" ein.
                        """.trimMargin())
                    Thread.sleep(5000)
                    println("Lass uns das Spiel starten...")
                    break
                }

                "2", "nein" -> {
                    println("Du hast Nein ausgewählt.\n")
                    break
                }

                else -> println("Ungültige Eingabe, probiere es erneut:")
            }
        }
    }

    /**
     * Bietet dem Benutzer die Möglichkeit, den Risikomodus zu wählen oder abzulehnen.
     * Im Risikomodus erhält der Spieler einen zusätzlichen Joker, aber die Sicherheitsstufe bei 16.000€ entfällt,
     * wodurch der Spieler auf 500€ zurückfallen kann.
     */
    private fun riskOrNot() {
        println("""|Bitte wähle aus, ob du den Risikomodus spielst oder nicht.
        |Bei Risiko erhältst du einen zusätzlichen Joker, ABER,
        |die Sicherheitsstufe bei 16.000€ fällt weg. Du kannst also auf
        |500€ runterfallen.
        |
        |1) Risikomodus wählen
        |2) KEIN Risikomodus
        """.trimMargin())

        while (true) {
            when (readln()) {
                "1" -> {
                    println("Du hast den Risikomodus gewählt und erhältst einen Zusatzjoker.\n")
                    this.joker.add(Zusatzjoker())
                    risiko = true
                    break
                }

                "2" -> {
                    println("Du hast den Risikomodus abgelehnt. Du erhältst daher nur die Standardjoker.\n")
                    break
                }

                else -> println("Ungültige Eingabe, probiere es erneut:")
            }
        }
    }

    private fun nextQuestion(player: Player) {
        while (true) {
            var question = this.newQuestion(this.round)
            question.getQuestion(player, this)
            var answer = question.chooseSolution(player, this, question)
            if (answer == -1) { // Beim negativen Wert wurde ein Joker genutzt, daher überspringen wir die weitere Auswertung
                println("Es wurde ein Joker genutzt.") // SPÄTER ENTFERNEN
                this.round++
                continue
            }
            println("Deine Antwort wurde eingeloggt...\n")
            question.getQuestionLoggedIn(answer)
            Thread.sleep(6000)
            println("\nSchauen wir mal, ob das so stimmt...\n")
            Thread.sleep(6000)
            question.getAnswer(answer)
            if (question.answer == answer) {
                if (this.round == 14) {
                    println("!!!!!!!DU BIST MILLIONÄR!!!!!")
                    Thread.sleep(5000)
                    hauptMenue(player)
                    return
                }
                println("\nDeine Antwort war richtig! Glückwunsch!\n")
                Thread.sleep(3000)
                println("\nKommen wir zur nächsten Frage.\n")
                Thread.sleep(6000)
                this.round++
                continue
            } else {
                println("Deine Antwort war leider falsch...\n")
                println("Die richtige Antwort wäre gewesen: ${question.options[question.answer]}")
                if (round > 9 && !risiko) {
                    println("Du fällst auf deine letzte Sicherheitsstufe zurück und erhältst 16.000€!")
                } else if (round > 4) {
                    println("Du fällst auf deine letzte Sicherheitsstufe zurück und erhältst 500€!")
                }
                Thread.sleep(2000)
                println("Wir bringen dich sofort ins Hauptmenü zurück.\n")
                Thread.sleep(2000)
                hauptMenue(player)
                break
            }
        }
    }

    private fun singleOrMultiPlayer(player: Player): Player {
        println("Wähle aus, ob Einzel oder Mehrspieler, ${player.name}:")
        println("1) Einzelspieler\n2) Mehrspieler\n")

        while (true) {
            when (readln()) {
                "1" -> {
                    println("Du hast Einzelspieler ausgewählt. Das Spiel startet sofort.")
                    Thread.sleep(5000)
                    return player
                }
                "2" -> return handleMultiPlayer(player)
                else -> println("Ungültige Eingabe. probier es erneut:")
            }
        }
    }

    private fun handleMultiPlayer(player: Player): Player {
        var howManyPlayer = 0
        val playersList = mutableListOf<Player>()
        println("Wie viele Spieler nehmen außer dir noch teil? (Mit stop kannst du abbrechen und als Einzelspieler starten)")
        while (true) {
            val input = readln()
            if (input == "stop") return player
            when (input.toIntOrNull()) {
                null -> println("Ungültige Eingaben, gib bitte nur Zahlen ein:")
                !in 1..4 -> println("Du musst mind. 1 und maximal 4 weitere Spieler angeben.")
                else -> {
                    howManyPlayer = input.toInt()
                    break
                }
            }
        }

        // Wir fügen der Spielerliste initial den bereits angelegten Spieler hinzu
        playersList.add(player)
        // Wir fügen nun so viele neue Spieler hinzu, wie angegeben wurden
        repeat(howManyPlayer) {
            println("Spieler ${it+2}")
            playersList.add(newPlayer())
        }

        println("\nFolgende Spieler nehmen an der Qualifizierungsrunde teil:\n")
        var playerNumber = 1
        for (player in playersList) {
            println("Spieler $playerNumber: ${player.name}")
            playerNumber++
        }
         return handleQualifying(playersList)
    }

    private fun handleQualifying(playersList: MutableList<Player>): Player {
        var winner: Player? = null
        // Wir speichern uns das Playerobjekt, außerdem in einem Pair die Antworten und Antwortzeiten der Spieler
        val playerAnswers = mutableMapOf<Player, Pair<String, Long>>()
        // Wir holen uns eine neue zufällige Sortierfrage
        val question = SortingQuestion.getNewQuestion()
        do {
            Thread.sleep(5000)
            println("\nNach der Reihe müssen nun alle Spieler die gleiche Frage beantworten.")
            println("\nDa alle aus Fairnessgründen die gleiche Frage bekommen, darf niemand beim anderen zuschauen.\n")
            Thread.sleep(6000)
            println("Ihr erhaltet eine Frage und müsst die Optionen (a bis d) entsprechend der Frage sortieren.")
            println("Gebt dazu einfach die Reihenfolge an, zb: cabd")
            Thread.sleep(10000)
            println("\nWir beginnen nun mit Spieler 1 (${playersList.first().name})")
            println("Alle anderen ab sofort wegschauen!!!!")
            Thread.sleep(6000)
            var playerNumber = 1
            for (player in playersList) {
                println("Spieler $playerNumber (${player.name}), mach dich bereit:")
                Thread.sleep(5000)
                (3 downTo 1).forEach {
                    println(it)
                    Thread.sleep(1000)
                }
                println(question.question)
                question.options.forEachIndexed { index, option ->
                    val char = 'a' + index
                    println("$char) $option")
                }
                println("\nGib nun deine Antwort ein:")
                println(question.answer) // NUR FÜR DEBUGGING
                // Wir speichern den aktuellen Zeitpunkt, ab dem der Spieler seine Antwort abgeben kann
                var startTimestamp = System.currentTimeMillis()
                // Wir holen uns die Antwort im Spieler
                var playerAnswer = readln().lowercase().trimMargin()
                // Da wir nun die Antwort haben, speichern wir den aktuellen Zeitpunkt erneut
                var endTimestamp = System.currentTimeMillis()
                // Wir berechnen nun die Dauer anhand der beiden Zeitstände
                var difference = endTimestamp - startTimestamp
                // Wir fügen der Map playerAnswers sowohl das Playerobject, als auch die Antwort des Spielers und der Zeitdifferenz hinzu
                playerAnswers[player] = Pair(playerAnswer, difference)
                println("Deine Antwort wurde gespeichert. Wir löschen nun den Bildschirm.")
                Thread.sleep(5000)
                clearConsole()
                Thread.sleep(5000)
                playerNumber++
            }

            println("Alle Spieler haben geantwortet. Wir lösen nun auf:")
            Thread.sleep(4000)
            println("\nAuswertung der Antworten und Zeiten:")
            // Wir itieren über die Liste playerAnswers, in dem alle Spieler samt Antwort und Zeit gespeichert sind
            playerAnswers.forEach { (player, pair) ->
                // Entspricht val answer = pair.first / val time = pair.second
                val (answer, time) = pair
                // Wir wandeln die Milisekunden nun mit der Funktion formatMiliseconds ins Format 00:00.000 um.
                val formattedTime = formatMiliseconds(time)
                // Wir prüfen ob die abgegebene Antwort der korrekten entspricht, speichert einen Boolean
                val correctAnswer = answer == question.answer
                // Wir prüfen, ob der Spieler die richtige Lösung hatte und die Zeiten aller anderen Spieler höher sind. Falls ja, hat der aktuelle Spieler gewonnen und wird grün markiert.
                // It = aktuelles Map-Entry Paar aus Player und Pair (answer, time). value.second ist also jeweils die Zeit der anderen Spieler, über die wir itieren und pair.second die Zeiten des aktuellen Spielers über den wir itieren.
                val mark = if (correctAnswer && playerAnswers.all { it.value.second >= pair.second }) "\u001B[32m" else ""
                println("${mark}Spieler: ${player.name}, Antwort: $answer, Zeit: $formattedTime \u001B[0m")
            }

            // HIER NOCH KORREKT DOKUMENTIEREN
            println()
            val fastestCorrectAnswer = playerAnswers.filter { it.value.first == question.answer }.minByOrNull { it.value.second }
            if (fastestCorrectAnswer != null) {
                println("Spieler ${fastestCorrectAnswer.key.name} hat mit einer Zeit von ${formatMiliseconds(fastestCorrectAnswer.value.second)} Millisekunden und der korrekten Antwort gewonnen!")
                winner = fastestCorrectAnswer.key
            } else {
                println("Kein Spieler hat mit einer korrekten Antwort und der niedrigsten Zeit gewonnen. Die Qualifizierungsrunde wird erneut durchgeführt.")
            }
        } while (winner == null)

        return winner
    }


    fun startGame(firstPlayer: Player) {
        println("\n" +
                "                                                                                                         \n" +
                "__        _______ ____   __        _____ ____  ____    __  __ ___ _     _     ___ ___  _   _ _   _ ____  \n" +
                "\\ \\      / | ____|  _ \\  \\ \\      / |_ _|  _ \\|  _ \\  |  \\/  |_ _| |   | |   |_ _/ _ \\| \\ | (_)_(_|  _ \\ \n" +
                " \\ \\ /\\ / /|  _| | |_) |  \\ \\ /\\ / / | || |_) | | | | | |\\/| || || |   | |    | | | | |  \\| | /_\\ | |_) |\n" +
                "  \\ V  V / | |___|  _ <    \\ V  V /  | ||  _ <| |_| | | |  | || || |___| |___ | | |_| | |\\  |/ _ \\|  _ < \n" +
                "   \\_/\\_/  |_____|_| \\_\\    \\_/\\_/  |___|_| \\_|____/  |_|  |_|___|_____|_____|___\\___/|_| \\_/_/ \\_|_| \\_\\\n" +
                "                                                                                                         \n")

        println("Willkommen bei Wer wird Millionär, ${firstPlayer.name}!")
        val startMusic = playSound("wwm.wav")
        val player = this.singleOrMultiPlayer(firstPlayer)
        this.gameRules()
        this.riskOrNot()
        println()
        println("Beginnen wir nun mit der ersten Frage.\n")
        startMusic.stop()
        Thread.sleep(3000)
        this.nextQuestion(player)
    }

}







