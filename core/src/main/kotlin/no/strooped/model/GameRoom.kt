package no.strooped.model

class GameRoom(val player: Player, val gamePin: String, var currentTask: Task? = null)
