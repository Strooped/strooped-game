package no.strooped.service

import no.strooped.model.Task

typealias TaskStartCallback = (Task) -> Unit

class GameLifecycleService {

    fun onNextTask(callback: TaskStartCallback) {
        // socketService.on("task:start") {
        //
        //    callback(Task(...))
        // }
    }
    fun onTaskTimeout(callback: TaskStartCallback) {
        // socketService.on("task:end") {
        //
        //    callback(...)
        // }
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
