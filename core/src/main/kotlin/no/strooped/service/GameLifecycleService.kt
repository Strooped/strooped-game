package no.strooped.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import no.strooped.model.Task
import no.strooped.model.Answer

typealias TaskStartCallback = (Task) -> Unit
typealias RoundEndCallback = (Int) -> Unit
typealias GameEndCallback = (Int) -> Unit

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
    fun sendAnswer(answer: Answer) {
        socketService.sendAnswer(answer.toJson())
    }
    fun onRoundEnd(callback: RoundEndCallback) {
        socketService.onEvent("round:ending") {
            print(it)
            val placement = it["placement"] as Int
            callback(placement)
        }
    }
    fun onGameEnd(callback: GameEndCallback) {
        socketService.onEvent("game:ending") {
            val placement = it["placement"] as Int
            callback(placement)
        }
}
}
