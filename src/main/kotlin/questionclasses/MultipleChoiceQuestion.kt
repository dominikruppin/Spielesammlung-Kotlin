package questionclasses

import classes.Player
import gameclasses.WWM
import hauptMenue
import jokerclasses.*
import kotlin.concurrent.thread
import kotlin.system.exitProcess

class MultipleChoiceQuestion(
        question: String,
        difficulty: String,
        var options: List<String>,
        var answer: Int
): Question(question, difficulty) {

    // Die Gewinnsummen von Wer Wird Millionär (Frage 1: 50€, Frage 2: 100€ usw...)
    private val level = listOf(50, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 500000, 1000000)

    /**
     * Zeigt die Frage und die Antwortoptionen für die Instanz einer Frage an.
     *
     * @param player Der aktuelle Spieler.
     * @param wwm Die Instanz des "Wer wird Millionär"-Spiels, die Informationen zur aktuellen Runde enthält.
     */
    fun getQuestion(player: Player, wwm: WWM) {
        println("Frage ${wwm.round + 1} für %,d€:".format(level[wwm.round]))
        println(this.question)
        this.options.forEachIndexed { index, option ->
            val char = 'a' + index
            println("$char) $option")
        }
        println("Durch die Eingabe von \"stop\" kannst du jederzeit aufhören.\nDurch die Eingabe von \"Joker\" kannst du deine Joker nutzen.")
    }

    /**
     * Zeigt die aktuelle Frage und die Antwortoptionen an, dabei ist die ausgewählte
     * Antwort Gelb hinterlegt.
     *
     * @param choose Der Index der Antwortoption. Also die vom Spieler gewählte Antwortoption.
     */
    fun getQuestionLoggedIn(choose: Int) {
        println(this.question)
        this.options.forEachIndexed { index, option ->
            val char = 'a' + index
            val choosedOption = 'a' + choose
            if (char == choosedOption) {
                println("\u001B[33m$char) $option\u001B[0m")
            } else {
                println("$char) $option")
            }
        }
    }

    /**
     * Zeigt die Frage und die Antwortoptionen an. Dabei werden die richtige Lösung und falls
     * falsch gewählte Option farblich markiert (mit grün und rot)
     *
     * @param choose Der Index der Antwortoption. Also die vom Spieler gewählte Antwortoption.
     */
    fun getAnswer(choose: Int) {
        println(this.question)
        this.options.forEachIndexed { index, option ->
            val char = 'a' + index
            val choosedOption = 'a' + choose
            if (char == choosedOption && choose == this.answer) {
                println("\u001B[32m$char) $option\u001B[0m")
            } else if (char == choosedOption && choose != this.answer) {
                println("\u001B[31m$char) $option\u001B[0m")
            } else if (index == this.answer) {
                println("\u001B[32m$char) $option\u001B[0m")
            } else {
                println("$char) $option")
            }
        }
    }

    /**
     * Erlaubt dem Spieler, eine Lösung für die aktuelle Frage auszuwählen. Der Spieler kann entweder eine Antwort auswählen,
     * einen Joker verwenden oder das Spiel beenden.
     *
     * @param player Der aktuelle Spieler.
     * @param wwm Die Instanz des "Wer wird Millionär"-Spiels, die Informationen zur aktuellen Runde enthält.
     * @param question Die MultipleChoice-Frage, für die der Spieler eine Lösung wählt.
     *
     * @return Die Indexnummer der ausgewählten Antwort oder -1, wenn ein Joker verwendet wurde oder das Spiel beendet wurde.
     */

fun chooseSolution(player: Player, wwm: WWM, question: MultipleChoiceQuestion): Int {
        var answer = 0
        var userInput: String? = null

        // Funktion zur Eingabeüberwachung mit Zeitlimit
        val inputThread = thread(start = true) {
            userInput = readln()
        }

        println("Bitte gib deine Lösung ein, du hast dafür maximal 60 Sekunden Zeit:")
        inputThread.join(60000) // Warte maximal 60 Sekunden auf Benutzereingabe

        if (inputThread.isAlive) { // Falls der Thread noch läuft (keine Eingabe innerhalb von 60 Sekunden)
            println("Du hast innerhalb der Zeit keine Antwort gegeben, das Spiel ist vorbei.")
            exitProcess(0)
        } else { // Benutzereingabe innerhalb des Zeitlimits erhalten
            when (userInput) {
                "a" -> answer = 0
                "b" -> answer = 1
                "c" -> answer = 2
                "d" -> answer = 3
                "joker" -> {
                    if (wwm.joker.size < 1) {
                        println("Du hast keine Joker mehr.")
                    } else {
                        // getJoker returned einen Boolean. Bei true fand die Auswertung schon statt, bei false nicht
                        if (Joker().getJoker(player, wwm, question)) {
                            // Wenn die Auswertung schon war (true), dann returnen wir -1, damit wir das wissen
                            return -1
                        }
                    }
                }

                "stop" -> {
                    println("Möchtest du wirklich aufhören und die %,d€ mitnehmen?".format(level[wwm.round - 1]))
                    if (wwm.joker.size > 0) {
                        println("Denk daran, du besitzt noch folgende Joker: ${wwm.joker}")
                    }
                    while (true) {
                        println("1) Ja\n2) Nein")
                        when (readln().lowercase()) {
                            "1", "ja" -> {
                                println("Weise Entscheidung, ich gratuliere dir zu den gewonnenen %,d€!".format(level[wwm.round - 1]))
                                Thread.sleep(2000)
                                println("Es geht gleich zurück ins Hauptmenü.")
                                Thread.sleep(8000)
                                hauptMenue(player)
                                return answer
                            }

                            "2", "nein" -> {
                                println("Ein Zocker wie ich sehe... machen wir weiter!")
                                println("Bitte gib deine Antwort zur bereits gestellten Frage ein:")
                                return answer
                            }

                            else -> println("Ungültige Eingabe, versuche es erneut:")
                        }
                    }
                }

                else -> println("Ungültige Eingabe, probiere es erneut:")
            }
        }
        return answer
    }
}

// Multiple Choice Questions:
// Die Fragen wurden aus dem Internet kopiert.
val multipleChoiceQuestions = listOf(
        MultipleChoiceQuestion(
                question = "Was ist die Hauptstadt von Deutschland?",
                options = listOf("Berlin", "München", "Hamburg", "Köln"),
                answer = 0,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wer schrieb 'Faust'?",
                options = listOf("Friedrich Schiller", "Johann Wolfgang von Goethe", "Heinrich Heine", "Thomas Mann"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Was ist das chemische Symbol für Wasser?",
                options = listOf("O2", "H2O", "CO2", "NaCl"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wer hat die Mona Lisa gemalt?",
                options = listOf("Vincent Van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Bundesländer hat Deutschland?",
                options = listOf("14", "15", "16", "17"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Tier ist das schnellste an Land?",
                options = listOf("Elefant", "Gepard", "Falke", "Hase"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "In welchem Jahr fiel die Berliner Mauer?",
                options = listOf("1987", "1988", "1989", "1990"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Elemente hat das Periodensystem?",
                options = listOf("102", "118", "120", "125"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Wer ist der Autor von 'Harry Potter'?",
                options = listOf("J.K. Rowling", "Stephen King", "J.R.R. Tolkien", "George R.R. Martin"),
                answer = 0,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Was ist die größte Wüste der Welt?",
                options = listOf("Sahara", "Antarktische Wüste", "Arabische Wüste", "Gobi"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land hat die größte Bevölkerung?",
                options = listOf("USA", "China", "Indien", "Brasilien"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Herzen hat ein Oktopus?",
                options = listOf("1", "2", "3", "4"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "In welchem Jahr begann der Zweite Weltkrieg?",
                options = listOf("1914", "1939", "1945", "1950"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wer hat die Relativitätstheorie formuliert?",
                options = listOf("Isaac Newton", "Albert Einstein", "Nikola Tesla", "Marie Curie"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Was ist das größte Organ des menschlichen Körpers?",
                options = listOf("Herz", "Leber", "Haut", "Lunge"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welcher Planet ist der Sonne am nächsten?",
                options = listOf("Venus", "Erde", "Merkur", "Mars"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wer hat die Telefonie erfunden?",
                options = listOf("Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Michael Faraday"),
                answer = 0,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Zähne hat ein erwachsener Mensch normalerweise?",
                options = listOf("28", "30", "32", "34"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Element hat das chemische Symbol 'O'?",
                options = listOf("Gold", "Sauerstoff", "Osmium", "Silber"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land ist für seine Pyramiden bekannt?",
                options = listOf("Mexiko", "Ägypten", "Peru", "Indien"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Was ist die Hauptstadt von Australien?",
                options = listOf("Sydney", "Melbourne", "Canberra", "Perth"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Tier gilt als das schnellste im Wasser?",
                options = listOf("Delfin", "Hai", "Segelfisch", "Wal"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Kontinente gibt es auf der Erde?",
                options = listOf("5", "6", "7", "8"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wer ist der Autor von 'Der Herr der Ringe'?",
                options = listOf("J.K. Rowling", "J.R.R. Tolkien", "George R.R. Martin", "C.S. Lewis"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Was ist die am meisten gesprochene Sprache der Welt?",
                options = listOf("Englisch", "Mandarin", "Spanisch", "Hindi"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land ist bekannt als das Land der aufgehenden Sonne?",
                options = listOf("China", "Japan", "Südkorea", "Thailand"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Streifen hat die Flagge der Vereinigten Staaten?",
                options = listOf("13", "14", "15", "16"),
                answer = 0,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches ist das kleinste Knochen im menschlichen Körper?",
                options = listOf("Stapes (Steigbügel)", "Clavicula (Schlüsselbein)", "Fibula (Wadenbein)", "Metacarpal (Mittelhandknochen)"),
                answer = 0,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welcher Fluss ist der längste der Welt?",
                options = listOf("Amazonas", "Nil", "Jangtse", "Mississippi"),
                answer = 0,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Musikinstrument hat Tasten, Pedale und Saiten?",
                options = listOf("Gitarre", "Violine", "Klavier", "Harfe"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land gewann die Fußball-Weltmeisterschaft 2014?",
                options = listOf("Brasilien", "Argentinien", "Deutschland", "Niederlande"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Was ist die chemische Formel für Tafelsalz?",
                options = listOf("H2O", "CO2", "C12H22O11", "NaCl"),
                answer = 3,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welcher Planet unseres Sonnensystems ist als der Rote Planet bekannt?",
                options = listOf("Jupiter", "Mars", "Saturn", "Venus"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Organ des menschlichen Körpers produziert Insulin?",
                options = listOf("Leber", "Bauchspeicheldrüse", "Niere", "Magen"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land hat eine Stadt namens Istanbul?",
                options = listOf("Indien", "Indonesien", "Türkei", "Iran"),
                answer = 2,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Planeten hat unser Sonnensystem?",
                options = listOf("7", "8", "9", "10"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Wer hat die Theorie der Evolution durch natürliche Selektion vorgeschlagen?",
                options = listOf("Albert Einstein", "Isaac Newton", "Charles Darwin", "Stephen Hawking"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Metall ist flüssig bei Raumtemperatur?",
                options = listOf("Blei", "Eisen", "Quecksilber", "Aluminium"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land ist der größte Produzent von Kaffee?",
                options = listOf("Kolumbien", "Indonesien", "Äthiopien", "Brasilien"),
                answer = 3,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welche Farbe entsteht, wenn man Rot und Blau mischt?",
                options = listOf("Lila", "Grün", "Orange", "Braun"),
                answer = 0,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Buchstaben hat das deutsche Alphabet?",
                options = listOf("26", "27", "28", "29"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welcher Ozean ist der größte?",
                options = listOf("Atlantischer Ozean", "Indischer Ozean", "Pazifischer Ozean", "Südlicher Ozean"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "In welchem Land befindet sich der Taj Mahal?",
                options = listOf("Indien", "Pakistan", "Bangladesch", "Nepal"),
                answer = 0,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Tier wird oft als König der Tiere bezeichnet?",
                options = listOf("Tiger", "Löwe", "Elefant", "Gorilla"),
                answer = 1,
                difficulty = "easy"
        ),
        MultipleChoiceQuestion(
                question = "Welches Element hat das Periodensystem-Kürzel 'Au'?",
                options = listOf("Silber", "Gold", "Aluminium", "Argon"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welcher Fluss fließt durch Paris?",
                options = listOf("Rhein", "Themse", "Seine", "Donau"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Bundesstaaten hat die USA?",
                options = listOf("48", "50", "52", "54"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Tier ist das Nationaltier Schottlands?",
                options = listOf("Adler", "Einhorn", "Löwe", "Hirsch"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches chemische Element hat das Symbol 'K'?",
                options = listOf("Kalium", "Krypton", "Kohlenstoff", "Kalzium"),
                answer = 0,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land ist bekannt für seine Tulpen?",
                options = listOf("Frankreich", "Niederlande", "Belgien", "Deutschland"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Tasten hat ein modernes Klavier?",
                options = listOf("66", "76", "88", "98"),
                answer = 2,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welcher deutsche Komponist schrieb die 9. Sinfonie?",
                options = listOf("Johann Sebastian Bach", "Wolfgang Amadeus Mozart", "Ludwig van Beethoven", "Johannes Brahms"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land hat die längste Küstenlinie?",
                options = listOf("USA", "Russland", "Kanada", "Australien"),
                answer = 2,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Wie viele Farben hat ein Regenbogen?",
                options = listOf("5", "6", "7", "8"),
                answer = 2,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welches ist das größte Land der Welt?",
                options = listOf("China", "Russland", "USA", "Kanada"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Organ filtert das Blut?",
                options = listOf("Herz", "Leber", "Niere", "Lunge"),
                answer = 2,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land produziert die meisten Filme jährlich?",
                options = listOf("USA", "Indien", "China", "Nigeria"),
                answer = 1,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welches Gebäude war das höchste der Welt vor dem Burj Khalifa?",
                options = listOf("Taipei 101", "Empire State Building", "Shanghai Tower", "Petronas Towers"),
                answer = 0,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welches ist das meistverkaufte Buch der Welt nach der Bibel?",
                options = listOf("'Don Quijote'", "'Harry Potter'", "'Worte des Vorsitzenden Moa Tsetung'", "'Das Kapital'"),
                answer = 2,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land ist Gastgeber der Olympischen Spiele 2024?",
                options = listOf("USA", "Frankreich", "Japan", "Australien"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches ist das härteste natürliche Material auf der Erde?",
                options = listOf("Stahl", "Diamant", "Granit", "Saphir"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches ist das am häufigsten vorkommende Element im Universum?",
                options = listOf("Helium", "Sauerstoff", "Kohlenstoff", "Wasserstoff"),
                answer = 3,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welche Sprache hat die meisten Muttersprachler?",
                options = listOf("Englisch", "Mandarin", "Spanisch", "Arabisch"),
                answer = 1,
                difficulty = "medium"
        ),
        MultipleChoiceQuestion(
                question = "Welches Land ist der größte Inselstaat der Welt?",
                options = listOf("Indonesien", "Madagaskar", "Philippinen", "Neuseeland"),
                answer = 0,
                difficulty = "strong"
        ),
        MultipleChoiceQuestion(
                question = "Welches Tier wird als 'Schiff der Wüste' bezeichnet?",
                options = listOf("Kamel", "Pferd", "Esel", "Elefant"),
                answer = 0,
                difficulty = "easy"
        )
)
