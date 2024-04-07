package gameclasses

import classes.Player
import hauptMenue

class Wordmix {

    companion object {
        fun use(player: Player) {
            println("Willkommen zu Wordmix, ${player.name}!")
            println("Mit der Eingabe von \"stop\" kannst du Wordmix jederzeit beenden.")
            println("Mit der Eingabe von \"next\" kannst du dir ein neues Wort geben lassen.")
            while (true) {
                val word = wordMixWords.random()
                // Der String wird in eine MutableList von Chars umgewandelt, mischt dann die Liste und gibt sie wieder als String aus
                val shuffleWord = word.toCharArray().toList().shuffled().joinToString("")
                println("Entwirre folgendes Wort: $shuffleWord")
                var guess = readln().lowercase()
                while (guess != word.lowercase()) {
                    when (guess) {
                        "next" -> {
                            println("Du bekommst nun ein neues Wort...")
                            Thread.sleep(3000)
                            break
                        }
                        "stop" -> {
                            println("Du hast Wordmix beendet. Wir bringen dich zurück ins Hauptmenü.")
                            Thread.sleep(4000)
                            hauptMenue(player)
                            return
                        }
                    }
                    println("Das war falsch. Probiere es erneut.")
                    guess = readln()
                }
                if (guess.lowercase() == word.lowercase()) {
                    println("Korrekt! Du hast das richtige Wort erraten!")
                }
            }
        }
    }
}

private val wordMixWords = listOf(
        "Apfel", "Banane", "Orange", "Erdbeere", "Kirsche",
        "Tisch", "Stuhl", "Sofa", "Schreibtisch", "Regal",
        "Hund", "Katze", "Vogel", "Fisch", "Hamster",
        "Auto", "Fahrrad", "Motorrad", "Bus", "Zug",
        "Buch", "Zeitung", "Magazin", "Roman", "Kochbuch",
        "Haus", "Wohnung", "Villa", "Hütte", "Schloss",
        "Computer", "Laptop", "Tablet", "Smartphone", "Drucker",
        "Schule", "Universität", "Kindergarten", "Schüler", "Lehrer",
        "Musik", "Film", "Buch", "Kunst", "Theater",
        "Fußball", "Basketball", "Tennis", "Golf", "Schwimmen",
        "Kaffee", "Tee", "Milch", "Saft", "Wasser",
        "Sommer", "Winter", "Frühling", "Herbst", "Jahreszeiten",
        "Urlaub", "Reise", "Abenteuer", "Entdeckung", "Erholung",
        "Kochen", "Backen", "Grillen", "Essen", "Trinken",
        "Liebe", "Freundschaft", "Familie", "Beziehung", "Ehe",
        "Glück", "Erfolg", "Zufriedenheit", "Wohlstand", "Gesundheit",
        "Abenteuer", "Fantasie", "Mystery", "Krimi", "Science-Fiction",
        "Freiheit", "Gleichheit", "Gerechtigkeit", "Frieden", "Solidarität",
        "Hobby", "Sport", "Musik", "Malen", "Lesen",
        "Mond", "Sonne", "Sterne", "Galaxie", "Planet",
        "Regen", "Sonne", "Wind", "Schnee", "Wolken",
        "Morgen", "Mittag", "Abend", "Nacht", "Zeit",
        "Arbeit", "Beruf", "Karriere", "Erfolg", "Ziel",
        "Freizeit", "Entspannung", "Erholung", "Genuss", "Spaß",
        "Geld", "Finanzen", "Investition", "Budget", "Reichtum",
        "Garten", "Blumen", "Bäume", "Pflanzen", "Natur",
        "Licht", "Farben", "Schatten", "Helligkeit", "Dunkelheit",
        "Stadt", "Dorf", "Land", "Welt", "Globus",
        "Wissenschaft", "Forschung", "Entdeckung", "Innovation", "Technologie",
        "Gesellschaft", "Kultur", "Tradition", "Gemeinschaft", "Vielfalt",
        "Kunst", "Malerei", "Skulptur", "Bildhauerei", "Design",
        "Gesundheit", "Medizin", "Fitness", "Ernährung", "Wohlbefinden",
        "Geist", "Seele", "Körper", "Verstand", "Bewusstsein",
        "Leben", "Existenz", "Dasein", "Sein", "Lebensfreude",
        "Träume", "Visionen", "Ziele", "Ambitionen", "Erfüllung",
        "Musik", "Kunst", "Literatur", "Film", "Theater",
        "Sport", "Fußball", "Basketball", "Tennis", "Golf",
        "Essen", "Trinken", "Kochen", "Backen", "Genuss",
        "Liebe", "Freundschaft", "Familie", "Beziehung", "Partnerschaft",
        "Natur", "Umwelt", "Ökosystem", "Tiere", "Pflanzen",
        "Wetter", "Klima", "Temperaturen", "Sonne", "Regen",
        "Glück", "Erfolg", "Zufriedenheit", "Lebensfreude", "Gesundheit",
        "Reisen", "Abenteuer", "Entdeckungen", "Erholung", "Entspannung",
        "Arbeit", "Beruf", "Karriere", "Job", "Beschäftigung",
        "Studium", "Universität", "Schule", "Lernen", "Bildung",
        "Technologie", "Innovation", "Fortschritt", "Entwicklung", "Digitalisierung",
        "Zukunft", "Visionen", "Träume", "Hoffnungen", "Perspektiven",
        "Freiheit", "Demokratie", "Menschenrechte", "Gerechtigkeit", "Solidarität",
        "Frieden", "Verständigung", "Diplomatie", "Toleranz", "Dialog",
        "Kunst", "Kultur", "Musik", "Literatur", "Theater",
        "Gesundheit", "Medizin", "Ernährung", "Fitness", "Wohlbefinden",
        "Natur", "Umwelt", "Ökologie", "Nachhaltigkeit", "Artenvielfalt",
        "Wissenschaft", "Forschung", "Entdeckung", "Innovation", "Technologie"
)
