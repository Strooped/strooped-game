package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ObjectMap
import no.strooped.service.GameLifecycleService
import no.strooped.service.JoinGameService
import no.strooped.service.SocketService
import no.strooped.view.screen.EndRoundScreen
import no.strooped.view.screen.JoinGameScreen
import no.strooped.view.screen.LobbyScreen
import no.strooped.view.screen.TaskScreen
import no.strooped.view.screen.components.ColorButton

const val TITLE = "Strooped"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()
    var socket: SocketService = SocketService()
    var joinGameService: JoinGameService = JoinGameService(socket)
    var gameLifecycleService: GameLifecycleService = GameLifecycleService(socket)

    override fun create() {
        loadScreens()
        inititalizeServices()
        screens.put(EndRoundScreen::class.java, EndRoundScreen(this))
        changeScreen(EndRoundScreen::class.java)
        gameLifecycleService.onNextTask {
            GameSingleton.taskNumber++
            screens.put(TaskScreen::class.java, TaskScreen(this, it))
            changeScreen(TaskScreen::class.java)
            // add task to singleton
            // showNextTask(task)
        }
        gameLifecycleService.onRoundEnd { // task is called round:ending..will receive a player object
            GameSingleton.room?.player?.let {
                it.placement = 1 // new placement
            }
            screens.put(EndRoundScreen::class.java, EndRoundScreen(this))
            changeScreen(EndRoundScreen::class.java)
            // show round scoreboard
        }
        gameLifecycleService.onGameEnd {
            // end game stuff
        }
    }

    override fun dispose() {
    }

    fun changeScreen(key: Class<out Screen>) {
        setScreen(screens.get(key))
    }

    fun connectToGame(username: String, joinPin: String) {
        // Call JoinGameService to connect
        // ...
        joinGameService.joinGame(username, joinPin) {
            GameSingleton.room = it
            screens.put(LobbyScreen::class.java, LobbyScreen(this, it))
            changeScreen(LobbyScreen::class.java)
        }
    }

    fun exitLobby() {
        changeScreen(JoinGameScreen::class.java)
    }

    fun answerTask(answer: ColorButton) {
        GameSingleton.room?.currentTask?.let {
            gameLifecycleService.sendAnswer(it, answer.getStringColor())
        }
    }

    private fun loadScreens() {
        screens.put(JoinGameScreen::class.java, JoinGameScreen(this))
    }

    private fun inititalizeServices() {
        // val joinGameService = JoinGameService(socket)
    }
}
