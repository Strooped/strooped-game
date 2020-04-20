package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ObjectMap
import no.strooped.service.GameLifecycleService
import no.strooped.service.JoinGameService
import no.strooped.service.SocketService
import no.strooped.view.screen.JoinGameScreen
import no.strooped.view.screen.LobbyScreen
import no.strooped.view.screen.TaskScreen

const val TITLE = "Strooped"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()
    var socket: SocketService = SocketService()
    var joinGameService: JoinGameService = JoinGameService(socket)
    var gameLifecycleService: GameLifecycleService = GameLifecycleService(socket)

    override fun create() {
        loadScreens()
        inititalizeServices()
        changeScreen(JoinGameScreen::class.java)
        gameLifecycleService.onNextTask {
            GameSingleton.taskNumber++
            screens.put(TaskScreen::class.java, TaskScreen(this, it))
            changeScreen(TaskScreen::class.java)
            // add task to singleton
            // showNextTask(task)
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

    fun changeScreen(key: Class<out Screen>) {
        setScreen(screens.get(key))
//        handle(GameEvent("SCREEN_CHANGE").set("SCREEN", screens.get(key)))
    }

    fun connectToGame(username: String, joinPin: String) {
        // Call JoinGameService to connect
        // ...
        joinGameService.joinGame(username, joinPin) {
            GameSingleton.room = it
            screens.put(LobbyScreen::class.java, LobbyScreen(this, it))
            changeScreen(LobbyScreen::class.java)
        }
        // success, do this usually, now for testing I invoke TaskScreen
        /*val task = Task("1", "#FF0000", listOf(
            "#FF0000", // red
            "#33FF4F", // green
            "#F3FF33", // yellow
            "#337AFF", // blue
            "#FF33E9", // pink
            "#8633FF" // purple
        ))
        GameSingleton.room!!.currentTask = task
        screens.put(TaskScreen::class.java, TaskScreen(this, room))
        changeScreen(TaskScreen::class.java)*/
    }

    fun exitLobby() {
        changeScreen(JoinGameScreen::class.java)
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
        val joinGameService = JoinGameService(socket)
    }
}
