package no.strooped.model

data class GameRoom(val player: Player, val gamePin: String, var currentTask: Task? = null)
