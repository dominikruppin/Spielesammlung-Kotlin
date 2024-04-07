package questionclasses

class SortingQuestion(
        question: String,
        difficulty: String,
        var options: List<String>,
        var answer: String
): Question(question, difficulty) {

        // Wenn wir es als companion object definieren, können wir ohne eine Instanz darauf zugreifen
        /**
         * Erzeugt eine neue Sortierungsfrage, die zufällig aus der Liste der verfügbaren Sortierungsfragen ausgewählt wird.
         *
         * @return Eine zufällig ausgewählte Sortierungsfrage.
         */
        companion object {
                fun getNewQuestion(): SortingQuestion {
                        return sortingQuestions.random()
                }
        }

}
val sortingQuestions = listOf(
        SortingQuestion(
                "Ordnen Sie die folgenden Monate nach ihrer Reihenfolge im Jahr, beginnend mit dem ersten:",
                "medium",
                listOf("März", "Juni", "September", "Dezember"),
                "dcba"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Wochentage nach ihrer Reihenfolge in der Woche, beginnend mit dem ersten:",
                "easy",
                listOf("Montag", "Mittwoch", "Freitag", "Sonntag"),
                "cbad"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Jahreszeiten nach ihrer Reihenfolge im Jahr, beginnend mit dem ersten:",
                "hard",
                listOf("Frühling", "Sommer", "Herbst", "Winter"),
                "dbac"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Elemente nach ihrer Ordnungszahl im Periodensystem, beginnend mit dem kleinsten:",
                "easy",
                listOf("Wasserstoff", "Helium", "Sauerstoff", "Eisen"),
                "adbc"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Disney-Prinzessinnen nach dem Jahr ihres Filmdebüts, beginnend mit der frühesten:",
                "medium",
                listOf("Schneewittchen", "Arielle", "Mulan", "Rapunzel"),
                "bacd"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Länder nach ihrer Einwohnerzahl, beginnend mit dem bevölkerungsreichsten:",
                "hard",
                listOf("China", "Indien", "USA", "Indonesien"),
                "cdab"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden US-Bundesstaaten nach ihrer Fläche, beginnend mit dem größten:",
                "medium",
                listOf("Alaska", "Texas", "Kalifornien", "Montana"),
                "dcab"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Obstsorten nach ihrem Vitamin-C-Gehalt, beginnend mit dem höchsten:",
                "easy",
                listOf("Orangen", "Äpfel", "Bananen", "Kiwi"),
                "dbac"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Olympischen Spiele nach dem Jahr ihres Gastgeberlandes, beginnend mit dem frühesten:",
                "hard",
                listOf("Athen", "Peking", "Rio de Janeiro", "Tokio"),
                "abcd"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Tierarten nach ihrer durchschnittlichen Lebenserwartung, beginnend mit der längsten:",
                "medium",
                listOf("Elefant", "Hund", "Schildkröte", "Maus"),
                "badc"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Musikgenres nach ihrem Ursprungsland, beginnend mit dem frühesten:",
                "easy",
                listOf("Jazz", "Reggae", "K-Pop", "Samba"),
                "acbd"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Schauspieler nach ihrem Geburtsjahr, beginnend mit dem ältesten:",
                "medium",
                listOf("Marlon Brando", "Tom Hanks", "Leonardo DiCaprio", "Brad Pitt"),
                "dcab"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden PC-Hersteller nach dem Jahr ihrer Gründung, beginnend mit dem frühesten:",
                "hard",
                listOf("Apple", "Microsoft", "IBM", "Dell"),
                "cbad"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Meeresbewohner nach ihrer durchschnittlichen Größe, beginnend mit dem größten:",
                "medium",
                listOf("Pottwal", "Killerwal", "Delfin", "Robbe"),
                "dacb"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Buchstaben nach ihrer Position im Alphabet, beginnend mit dem ersten:",
                "easy",
                listOf("A", "D", "G", "J"),
                "abcd"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Olympischen Sportarten nach dem Jahr ihrer erstmaligen Austragung, beginnend mit dem frühesten:",
                "hard",
                listOf("Leichtathletik", "Schwimmen", "Radsport", "Tennis"),
                "cadb"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden US-Präsidenten nach ihrem Amtsantritt, beginnend mit dem ersten:",
                "medium",
                listOf("George Washington", "Abraham Lincoln", "Franklin D. Roosevelt", "Barack Obama"),
                "abcd"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Marvel-Superhelden nach dem Jahr ihres ersten Comic-Auftritts, beginnend mit dem frühesten:",
                "hard",
                listOf("Spider-Man", "Iron Man", "Hulk", "Black Widow"),
                "cdab"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Pflanzenteile nach ihrer Funktion, beginnend mit der Nahrungsspeicherung:",
                "easy",
                listOf("Wurzel", "Blatt", "Stängel", "Blüte"),
                "bcad"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Haustiere nach ihrer Domestikation durch den Menschen, beginnend mit dem frühesten:",
                "medium",
                listOf("Hund", "Katze", "Pferd", "Hamster"),
                "bacd"
        )
)
