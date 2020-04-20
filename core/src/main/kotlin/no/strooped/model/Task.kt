package no.strooped.model

data class Task(/*val id: String,*/ val correctAnswer: String, val possibleAnswers: List<String?>) {
    fun isCorrectAnswer(answer: String): Boolean {
        return correctAnswer == answer
    }
}
