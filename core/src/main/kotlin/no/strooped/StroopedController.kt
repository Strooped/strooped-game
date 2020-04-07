package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ObjectMap
import com.mygdx.game.states.GameStateManager
import com.mygdx.game.states.MenuState
import no.strooped.view.screen.JoinGameScreen

const val TITLE = "Paddle"

class StroopedController : Game() {
    private val screens: ObjectMap<Class<out Screen>, Screen> = ObjectMap()

    override fun create() {
        loadScreens()
        changeScreen(JoinGameScreen::class.java)
    }

    override fun dispose() {
//        batch!!.dispose()
    }

    fun changeScreen(key: Class<out Screen>) {
        setScreen(screens.get(key))
//        handle(GameEvent("SCREEN_CHANGE").set("SCREEN", screens.get(key)))
    }

    private fun loadScreens() {
        screens.put(JoinGameScreen::class.java, JoinGameScreen(this))
    }
}
