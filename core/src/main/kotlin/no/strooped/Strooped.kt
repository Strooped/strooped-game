package no.strooped

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.states.GameStateManager
import com.mygdx.game.states.MenuState

const val TITLE = "Paddle"

class Strooped : Game() {
    var batch: SpriteBatch? = null
    private var gsm: GameStateManager? = null

    override fun create() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        gsm!!.push(MenuState(gsm))
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm!!.update(Gdx.graphics.deltaTime)
        gsm!!.render(batch)
    }

    override fun dispose() {
        batch!!.dispose()
    }

    companion object {
        lateinit var TITLE: String
    }
}
