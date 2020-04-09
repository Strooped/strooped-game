package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Screen
import com.badlogic.gdx.utils.ObjectMap
import no.strooped.service.JoinGameService
import no.strooped.view.screen.JoinGameScreen

const val TITLE = "Paddle"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()

    lateinit var joinGameService: JoinGameService

    override fun create() {
        loadScreens()
        inititalizeServices()
        changeScreen(JoinGameScreen::class.java)
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
        joinGameService.joinGame(username, joinPin)
    }

    private fun loadScreens() {
        screens.put(JoinGameScreen::class.java, JoinGameScreen(this))
    }

    private fun inititalizeServices() {
        val joinGameService = JoinGameService(Object())
    }
}
