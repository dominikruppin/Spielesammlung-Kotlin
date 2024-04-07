package questionclasses

/**
 * Repräsentiert die Oberklasse für die Fragen.
 * Setzt voraus, dass eine Frage als String vorliegt und auch eine Schwierigkeitsstufe als String.
 * Schwierigkeitsstufen sind hier: easy, medium, strong (Könnte man auch als enum festlegen)
 */
open class Question(val question: String, val difficulty: String) {
}
