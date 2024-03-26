package gameclasses

import Player
import hauptMenue
import jokerclasses.*
import questionclasses.*

class WWM(name: String = "Wer wird Millionär"): Game(name) {
    val joker = mutableListOf<Joker>()
    var round = 0
    var risiko = false
    var easyQuestions: MutableList<MultipleChoiceQuestion>
    var mediumQuestions: MutableList<MultipleChoiceQuestion>
    var strongQuestions: MutableList<MultipleChoiceQuestion>

    init {
        this.joker.addAll(listOf(FiftyFiftyJoker()))
        this.easyQuestions = multipleChoiceQuestions.filter { it.difficulty == "easy" }.toMutableList()
        this.mediumQuestions = multipleChoiceQuestions.filter { it.difficulty == "medium" }.toMutableList()
        this.strongQuestions = multipleChoiceQuestions.filter { it.difficulty == "strong" }.toMutableList()
    }

    fun newQuestion(round: Int): MultipleChoiceQuestion {
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

    fun gameRules() {
        println("Möchtest du die Spielregeln lesen?\n1) Ja\n2) Nein")
        while (true) {
            when (readln().lowercase()) {
                "1", "ja" -> {
                    println("Du hast ja ausgewählt. Wir erklären dir nun die Spielregeln...")
                    println("""
                        |"Wer wird Millionär" ist ein Quizspiel, bei dem Kandidaten Fragen aus verschiedenen Wissensbereichen beantworten müssen, um Geldpreise zu gewinnen. 
                        |Die Fragen werden in aufsteigender Schwierigkeit gestellt, wobei jeder Frage ein höherer Geldbetrag zugeordnet ist, bis hin zur Million. 
                        |Der Kandidat hat vier jokerclasses.Joker, die ihm helfen können:
                        |
                        |- Publikumsjoker: Der Kandidat fragt das Publikum nach ihrer Meinung, welche Antwort richtig ist.
                        |
                        |- Telefonjoker: Der Kandidat darf eine Person seiner Wahl anrufen und um Hilfe bei der Beantwortung der Frage bitten.
                        |
                        |- 50:50-jokerclasses.Joker: Zwei falsche Antworten werden eliminiert, sodass dem Kandidaten nur noch zwei Antwortmöglichkeiten bleiben.
                        |
                        |- Zusatzjoker: Du kannst die Absicherung bei 16.000€ gegen einen jokerclasses.Joker tauschen. Es stehen Leute aus dem Publikum auf, welche 
                        |denken, dass sie die Lösung kennen.
                        |
                        |Um einen Joker zu nutzen, gib statt der Lösung der Frage einfach das Wort "Joker" ein.
                        """.trimMargin())
                    Thread.sleep(5000)
                    println("Lass uns das Spiel starten...")
                    break
                }

                "2", "nein" -> {
                    println("Du hast Nein ausgewählt. Lass uns das Spiel starten.")
                    break
                }

                else -> println("Ungültige Eingabe, probiere es erneut:")
            }
        }
    }

    fun riskOrNot() {
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
                    println("Du hast den Risikomodus gewählt und erhältst einen Zusatzjoker.")
                    // this.joker.add("Zusatzjoker")
                    break
                }

                "2" -> {
                    println("Du hast den Risikomodus abgelehnt. Du erhältst daher nur die Standardjoker.")
                    break
                }

                else -> println("Ungültige Eingabe, probiere es erneut:")
            }
        }
    }

    fun newQuestion(player: Player) {
        while (true) {
            var question = this.newQuestion(this.round)
            question.getQuestion(player, this)
            var answer = question.chooseSolution(player, this, question)
            if (answer == -1) { // Beim negativen Wert wurde ein Joker genutzt, daher überspringen wir die weitere Auswertung
                println("Es wurde ein Joker genutzt.") // SPÄTER ENTFERNEN
                this.round++
                continue // SPÄTER GEGEN CONTINUE AUSTAUSCHEN
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
                }
                println("\nDeine Antwort war richtig! Glückwunsch!\n")
                Thread.sleep(3000)
                println("\nKommen wir zur nächsten Frage.\n")
                Thread.sleep(6000)
                this.round++
                continue
            } else {
                println("Deine Antwort war leider falsch... damit scheidest du aus...\n")
                println("Die richtige Antwort wäre gewesen: ${question.options[question.answer]}")
                Thread.sleep(2000)
                println("Wir bringen dich sofort ins Hauptmenü zurück.\n")
                Thread.sleep(2000)
                hauptMenue(player)
                break
            }
        }
    }

    fun startGame(player: Player) {
        println("\n" +
                "                                                                                                         \n" +
                "__        _______ ____   __        _____ ____  ____    __  __ ___ _     _     ___ ___  _   _ _   _ ____  \n" +
                "\\ \\      / | ____|  _ \\  \\ \\      / |_ _|  _ \\|  _ \\  |  \\/  |_ _| |   | |   |_ _/ _ \\| \\ | (_)_(_|  _ \\ \n" +
                " \\ \\ /\\ / /|  _| | |_) |  \\ \\ /\\ / / | || |_) | | | | | |\\/| || || |   | |    | | | | |  \\| | /_\\ | |_) |\n" +
                "  \\ V  V / | |___|  _ <    \\ V  V /  | ||  _ <| |_| | | |  | || || |___| |___ | | |_| | |\\  |/ _ \\|  _ < \n" +
                "   \\_/\\_/  |_____|_| \\_\\    \\_/\\_/  |___|_| \\_|____/  |_|  |_|___|_____|_____|___\\___/|_| \\_/_/ \\_|_| \\_\\\n" +
                "                                                                                                         \n")

        println("Willkommen bei Wer wird Millionär, ${player.name}!")
        this.gameRules()
        this.riskOrNot()
        println()
        println("Beginnen wir nun mit der ersten Frage.\n")
        Thread.sleep(3000)
        this.newQuestion(player)
    }

}

    // Bis hier hin funktional. Es kann bereits ein Quiz gespielt werden.
    // TODO:
    // - Alle jokerclasses.Joker implementieren
    // - Sicherheitsstufen implementieren








