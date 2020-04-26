package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ObjectMap
import no.strooped.service.GameLifecycleService
import no.strooped.service.JoinGameService
import no.strooped.service.SocketService
import no.strooped.view.screen.EndGameScreen
import no.strooped.view.screen.EndRoundScreen
import no.strooped.view.screen.JoinGameScreen
import no.strooped.view.screen.LobbyScreen
import no.strooped.view.screen.TaskScreen
import no.strooped.view.screen.components.Answer

const val TITLE = "Strooped"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()
    private var socket: SocketService = SocketService()
    private var joinGameService: JoinGameService = JoinGameService(socket)
    private var gameLifecycleService: GameLifecycleService = GameLifecycleService(socket)

    override fun create() {
        openJoinScreen()
        gameLifecycleService.onNextTask {
            GameSingleton.taskNumber++
            GameSingleton.room?.apply { currentTask = it }
            screens.remove(TaskScreen::class.java)
            screens.put(TaskScreen::class.java, TaskScreen(this, it))
            changeScreen(TaskScreen::class.java)
        }
        gameLifecycleService.onRoundEnd {
            GameSingleton.room?.player?.apply {
                placement = it // new placement
            }
            screens.put(EndRoundScreen::class.java, EndRoundScreen())
            changeScreen(EndRoundScreen::class.java)
        }
        gameLifecycleService.onGameEnd {
            GameSingleton.room?.player?.apply {
                placement = it // new placement
            }
            screens.put(EndGameScreen::class.java, EndGameScreen(this))
            changeScreen(EndGameScreen::class.java)
        }
    }

    fun connectToGame(username: String, joinPin: String) {
        joinGameService.joinGame(username, joinPin) {
            GameSingleton.room = it
            screens.put(LobbyScreen::class.java, LobbyScreen(this))
            changeScreen(LobbyScreen::class.java)
        }
    }

    fun exitLobby() {
        changeScreen(JoinGameScreen::class.java)
    }
    fun openJoinScreen() {
        screens.clear()
        screens.put(JoinGameScreen::class.java, JoinGameScreen(this))
        changeScreen(JoinGameScreen::class.java)
    }

    fun answerTask(answer: Answer) {
        GameSingleton.room?.currentTask?.apply {
            gameLifecycleService.sendAnswer(answer)
        }
    }

    private fun changeScreen(key: Class<out Screen>) {
        setScreen(screens.get(key))
    }

    override fun dispose() {}
}
