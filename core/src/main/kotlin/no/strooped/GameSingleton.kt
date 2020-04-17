package no.strooped

import no.strooped.model.GameRoom

object GameSingleton {
    var room: GameRoom? = null
    var taskNumber = 0
}
