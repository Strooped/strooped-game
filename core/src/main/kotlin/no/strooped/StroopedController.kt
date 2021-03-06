package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import no.strooped.model.Answer
import no.strooped.service.GameLifecycleService
import no.strooped.service.JoinGameService
import no.strooped.service.SocketService
import no.strooped.singleton.GameSingleton
import no.strooped.view.screen.EndGameScreen
import no.strooped.view.screen.EndRoundScreen
import no.strooped.view.screen.JoinGameScreen
import no.strooped.view.screen.LobbyScreen
import no.strooped.view.screen.TaskScreen

const val TITLE = "Strooped"

class StroopedController : Game() {
    private var socket: SocketService = SocketService()
    private var joinGameService: JoinGameService = JoinGameService(socket)
    private var gameLifecycleService: GameLifecycleService = GameLifecycleService(socket)
    override fun create() {
        openJoinScreen()
        gameLifecycleService.onNextTask {
            GameSingleton.taskNumber++
            GameSingleton.room?.apply { currentTask = it }
            changeScreen(TaskScreen(this, it))
        }
        gameLifecycleService.onRoundEnd {
            GameSingleton.taskNumber = 0
            GameSingleton.room?.player?.apply {
                placement = it
            }
            changeScreen(EndRoundScreen(this))
        }
        gameLifecycleService.onGameEnd {
            GameSingleton.taskNumber = 0
            GameSingleton.room?.player?.apply {
                placement = it
            }
            changeScreen(EndGameScreen(this))
        }
    }

    fun connectToGame(username: String, joinPin: String) {
        joinGameService.joinGame(username, joinPin) {
            it.fold(
                { gameRoom ->
                    GameSingleton.room = gameRoom
                    changeScreen(LobbyScreen(this))
                },
                { err ->
                    changeScreen(JoinGameScreen(this, err.localizedMessage))
                }
            )
        }
    }
    fun openJoinScreen() {
        GameSingleton.taskNumber = 0
        changeScreen(JoinGameScreen(this))
    }

    fun answerTask(answer: Answer) {
        GameSingleton.room?.currentTask?.apply {
            gameLifecycleService.sendAnswer(answer)
        }
    }

    private fun changeScreen(screen: Screen) {
        setScreen(screen)
    }

    override fun dispose() {}
}
