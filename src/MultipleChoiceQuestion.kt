class MultipleChoiceQuestion(
        question: String,
        difficulty: String,
        var options: List<String>,
        var answer: Int
): Question(question, difficulty) {
}