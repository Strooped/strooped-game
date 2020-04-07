package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.states.GameStateManager
import com.mygdx.game.states.MenuState
import no.strooped.StroopedController

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
class JoinGameScreen(val controller: StroopedController) : Screen {
    var ui: Stage? = Stage(ScreenViewport())

    var batch: SpriteBatch? = null
    private var gsm: GameStateManager? = null

    override fun hide() {
    }

    override fun show() {
        batch = SpriteBatch()
        gsm = GameStateManager()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        gsm!!.push(MenuState(gsm))
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        gsm!!.update(Gdx.graphics.deltaTime)
        gsm!!.render(batch)

//        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        draw(delta);
//
//        ui?.let {
//            it.act(delta);
//            it.draw();
//        }
    }

    /**
     * Override this sucker to implement any custom drawing
     * @param delta The number of seconds that have passed since the last frame.
     */
    private fun draw(delta: Float) {}

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
        ui?.viewport?.update(width, height);
    }

    override fun dispose() {
        ui?.dispose()
        ui = null;
    }
}
