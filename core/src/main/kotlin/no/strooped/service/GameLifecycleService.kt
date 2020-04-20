package no.strooped.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import no.strooped.model.Task

typealias TaskStartCallback = (Task) -> Unit

class GameLifecycleService(
    var socketService: SocketService
) {
    fun onNextTask(callback: TaskStartCallback) {

        socketService.onEvent("task:start") {
            val mapper = jacksonObjectMapper()
            val taskJsonObject = it["task"].toString()
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            val task: Task = mapper.readValue(taskJsonObject)
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
