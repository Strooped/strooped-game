package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.TextureSizes

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */

class LobbyScreen(val controller: StroopedController) : Screen {
    var ui: Stage = Stage(ScreenViewport())

    var cam: OrthographicCamera = OrthographicCamera()
    var mouse: Vector3 = Vector3()
    var background: Texture = Texture("white.jpg")
    var logo: Texture = Texture("Strooped1.png")
    var joinBtn: Texture = Texture("join_game_button.png")
    var loadAnim: Array<Texture?>

    var elapsedTime = 0f
    lateinit var batch: SpriteBatch

    private var iterat = 0
    private val numberOfFrames = 4

    init {
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch = SpriteBatch()

        loadAnim = arrayOfNulls(numberOfFrames)
        loadAnim[0] = Texture("load1.jpg")
        loadAnim[1] = Texture("load2.jpg")
        loadAnim[2] = Texture("load3.jpg")
        loadAnim[3] = Texture("load4.jpg")
    }

    override fun hide() {}

    override fun show() {
        batch = SpriteBatch()
    }

    override fun render(delta: Float) {
        handleInput()
        elapsedTime += Gdx.graphics.deltaTime
        batch.projectionMatrix = cam.combined
        batch.begin()
        val backPosX = cam.position.x - cam.viewportWidth / 2f
        val backPosY = 0f
        batch.draw(
            background,
            backPosX,
            backPosY,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
        val loadPosX = (Gdx.graphics.width.toFloat() - TextureSizes.loadWidth()) * 0.5f
        val loadPosY = Gdx.graphics.height.toFloat() * 0.5f - TextureSizes.loadHeight() * 0.5f
        val refreshInterval = 0.1f
        if (elapsedTime > refreshInterval) {
            iterat++
            elapsedTime = 0f
            if (iterat == numberOfFrames) {
                iterat = 0
            }
        }
        batch.draw(loadAnim[iterat], loadPosX, loadPosY, TextureSizes.loadWidth(), TextureSizes.loadHeight())
        /*val logoPosX = (Gdx.graphics.width.toFloat() - TextureSizes.logoWidth()) * 0.5f
        val logoPosY = Gdx.graphics.height.toFloat() * 0.75f - TextureSizes.logoHeight() * 0.5f
        batch.draw(
            logo,
            logoPosX,
            logoPosY,
            TextureSizes.logoWidth(),
            TextureSizes.logoHeight()
        )
        val joinBtnPosX = (Gdx.graphics.width.toFloat() - TextureSizes.joinGameButtonWidth()) * 0.5f
        val joinBtnPosY = Gdx.graphics.height.toFloat() * 0.27f
        batch.draw(
            joinBtn,
            joinBtnPosX,
            joinBtnPosY,
            TextureSizes.joinGameButtonWidth(),
            TextureSizes.joinGameButtonHeight()
        )*/
        batch.end()
        ui.draw()
    }

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {
        ui.viewport?.update(width, height)
    }

    override fun dispose() {
        ui.dispose()
    }

    private fun handleInput() {
        if (Gdx.input.justTouched()) {
            val rec = Rectangle(
                (Gdx.graphics.width.toFloat() - TextureSizes.joinGameButtonWidth()) * 0.5f,
                Gdx.graphics.height.toFloat() * 0.27f,
                TextureSizes.joinGameButtonWidth(),
                TextureSizes.joinGameButtonHeight())
            val touchX = Gdx.input.x.toFloat()
            val touchY = cam.viewportHeight - Gdx.input.y
            if (rec.contains(Vector2(touchX, touchY))) {
                println("Touch")
                // gsm.set(PlayState(gsm))
            }
        }
    }
}
