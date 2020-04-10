package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ObjectMap
import no.strooped.service.JoinGameService
import no.strooped.view.screen.JoinGameScreen
import no.strooped.view.screen.LobbyScreen

const val TITLE = "Strooped"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()
    var username = ""
    var joinGameService: JoinGameService = JoinGameService(socketService = Any())

    override fun create() {
        loadScreens()
        inititalizeServices()
        changeScreen(JoinGameScreen(this))
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
        joinGameService.joinGame(username, joinPin)
        // success
        this.username = username
        changeScreen(LobbyScreen(this))
    }

    fun exitLobby(){
        changeScreen(JoinGameScreen(this))
    }

    private fun loadScreens() {
        screens.put(JoinGameScreen::class.java, JoinGameScreen(this))
    }

    private fun inititalizeServices() {
        val joinGameService = JoinGameService(Object())
    }
}
