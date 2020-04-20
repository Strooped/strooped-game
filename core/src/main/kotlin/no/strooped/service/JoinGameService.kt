package no.strooped.service

import no.strooped.model.GameRoom
import no.strooped.model.Player

class JoinGameService(
    private val socket: SocketService
) {

    fun joinGame(username: String, joinPin: String, callback: (GameRoom) -> Unit) {
        socket.onConnect {
            callback(GameRoom(Player(username), joinPin))
            println(it.id())
            it.emit("task:answer", "some text")
        }
        socket.connect(joinPin)
        println("Joining game... $username, $joinPin")
    }
}
