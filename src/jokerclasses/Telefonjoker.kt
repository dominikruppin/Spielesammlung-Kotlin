package jokerclasses

import Names
import questionclasses.MultipleChoiceQuestion

class Telefonjoker(name: String = "Telefonjoker"): Joker(name) {
    private val names = Names()
    fun use(question: MultipleChoiceQuestion) {
        println("Du hast den Telefonjoker ausgewählt.")
        val rightPeople = mutableMapOf<String, Int>()
        val wrongPeople = mutableMapOf<String, Int>()
        var falseAnswers = mutableListOf(0, 1, 2, 3)
        falseAnswers.remove(question.answer)
        do {
            val name = names.generateRandomName()
            rightPeople[name] = question.answer
        } while (rightPeople.size < 2)

        do {
            val name = names.generateRandomName()
            if (rightPeople.contains(name)) {
                continue
            }
            wrongPeople[name] = falseAnswers.random()
        } while (wrongPeople.isEmpty())


    }
}