package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ObjectMap
import no.strooped.model.GameRoom
import no.strooped.model.Player
import no.strooped.model.Task
import no.strooped.service.GameLifecycleService
import no.strooped.service.JoinGameService
import no.strooped.view.screen.JoinGameScreen
import no.strooped.view.screen.LobbyScreen

const val TITLE = "Strooped"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()
    var joinGameService: JoinGameService = JoinGameService(socketService = Any())
    var gameLifecycleService: GameLifecycleService = GameLifecycleService()

    override fun create() {
        loadScreens()
        inititalizeServices()
        changeScreen(JoinGameScreen(this))
        gameLifecycleService.onNextTask {
            // add task to singleton
            // showNextTask(task)
        }
        gameLifecycleService.onTaskTimeout {
            // disable answering
        }
        gameLifecycleService.onRoundEnd {
            // show round scoreboard
        }
        gameLifecycleService.onGameEnd {
            // end game stuff
        }
    }

    override fun dispose() {
//        batch!!.dispose()
    }

    fun changeScreen(screen: Screen) {
        setScreen(screen)
//        handle(GameEvent("SCREEN_CHANGE").set("SCREEN", screens.get(key)))
    }

    fun connectToGame(username: String, joinPin: String) {
        // Call JoinGameService to connect
        // ...
        GameSingleton.room = joinGameService.joinGame(username, joinPin)
        // success
        changeScreen(LobbyScreen(this))
    }

    fun exitLobby() {
        changeScreen(JoinGameScreen(this))
    }

    fun answerTask(answer: String) {
        GameSingleton.room?.currentTask?.let {
            gameLifecycleService.sendAnswer(it, answer)
        }
        //
    }

    private fun loadScreens() {
        screens.put(JoinGameScreen::class.java, JoinGameScreen(this))
    }

    private fun inititalizeServices() {
        val joinGameService = JoinGameService(Object())
    }
}
