package no.strooped.service

import no.strooped.model.GameRoom
import no.strooped.model.Player

class JoinGameService(
    private val socket: SocketService
) {

    fun joinGame(username: String, joinPin: String, callback: (Result<GameRoom>) -> Unit) {
        socket.onConnect {
            callback(it.map { GameRoom(Player(username), joinPin) })
        }
        socket.connect(joinPin, username)
    }
}
