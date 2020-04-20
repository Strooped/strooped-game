package no.strooped.service

import no.strooped.model.Task
import org.json.JSONArray
import org.json.JSONObject

typealias TaskStartCallback = (Task) -> Unit

class GameLifecycleService(
    var socketService: SocketService
) {
    fun onNextTask(callback: TaskStartCallback) {

        socketService.onEvent("task:start") {
            val taskJsonObject = it["task"] as JSONObject
            val correctAnswer = taskJsonObject["correctAnswer"] as String
            val possibleAnswersJsonArray = taskJsonObject["buttons"] as JSONArray
            val possibleAnswers = arrayOfNulls<String>(possibleAnswersJsonArray.length())
            for (i in 0 until possibleAnswersJsonArray.length()) {
                possibleAnswers[i] = possibleAnswersJsonArray[i] as String
            }
            val task = Task(/*"1",*/ correctAnswer, possibleAnswers.toList())
            callback(task)
        }
    }
fun onRoundEnd(callback: TaskStartCallback) {
// socketService.on("round:end") {
//
//    callback(...)
// }
}
fun onGameEnd(callback: TaskStartCallback) {
// socketService.on("game:end") {
//
//    callback(...)
// }
}
fun sendAnswer(task: Task, answer: String) {
// sendAnswer to socket
}
}
