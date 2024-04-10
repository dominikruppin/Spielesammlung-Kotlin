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
                listOf("Juni", "März", "Dezember", "September"),
                "badc"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Wochentage nach ihrer Reihenfolge in der Woche, beginnend mit dem ersten:",
                "easy",
                listOf("Montag", "Mittwoch", "Freitag", "Sonntag"),
                "abcd"
        ),
        SortingQuestion(
                "Ordnen Sie die folgenden Jahreszeiten nach ihrer Reihenfolge im Jahr, beginnend mit dem ersten:",
                "easy",
                listOf("Winter", "Sommer", "Herbst", "Frühling"),
                "dbca"
        )
)
