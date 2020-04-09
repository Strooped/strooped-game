package no.strooped.service

class JoinGameService(
    val socketService: Any
) {

    fun joinGame(username: String, joinPin: String): Any {
        println("Joining game... $username, $joinPin")
        return Object()
    }
}
