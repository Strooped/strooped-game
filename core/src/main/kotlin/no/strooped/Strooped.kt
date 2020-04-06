package no.strooped

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.states.GameStateManager
import com.mygdx.game.states.MenuState

class Strooped : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    private var gsm: GameStateManager? = null
    val TITLE = "Paddle"
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
