package no.strooped.singleton

import no.strooped.model.GameRoom

object GameSingleton {
    var room: GameRoom? = null
    var taskNumber = 0
}
