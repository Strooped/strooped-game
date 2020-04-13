package no.strooped.service

import no.strooped.model.GameRoom
import no.strooped.model.Player

class JoinGameService(
    val socketService: Any
) {

    fun joinGame(username: String, joinPin: String): GameRoom {
        println("Joining game... $username, $joinPin")
        return GameRoom(Player(username), joinPin)
    }
}
