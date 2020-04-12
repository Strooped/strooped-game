package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.TextureSizes
import no.strooped.view.screen.components.TextFieldInput
import no.strooped.view.screen.components.UIButton

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
class JoinGameScreen(private val controller: StroopedController) : Screen {

    var ui: Stage = Stage(ScreenViewport())
    lateinit var batch: SpriteBatch
    private val cam: OrthographicCamera = OrthographicCamera()
    private val backgroundPosition = Vector2(cam.position.x - cam.viewportWidth * 0.5f, 0f)
    private val logoPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.logo.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.75f - TextureSizes.logo.height * 0.5f
    )
    private val joinButtonPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.joinButton.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.27f
    )
    private val usernamePosition = Vector2(Gdx.graphics.width * 0.15f, Gdx.graphics.height * 0.5f)
    private val pinPosition = Vector2(Gdx.graphics.width * 0.15f, Gdx.graphics.height * 0.38f)
    private val background: Texture = Texture("white.jpg")
    private val logo: Texture = Texture("Strooped1.png")
    private val joinButton: UIButton = UIButton(
        "joinGameButton",
        "Join game",
        joinButtonPosition,
        TextureSizes.joinButton
    )
    private lateinit var username: TextFieldInput
    private lateinit var pin: TextFieldInput
    override fun hide() {}

    override fun show() {
        batch = SpriteBatch()
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        username = TextFieldInput(
            "Username",
            usernamePosition,
            TextureSizes.inputBox
        )
        ui.addActor(username)
        ui.addActor(username.label)

        pin = TextFieldInput(
            "Pin",
            pinPosition,
            TextureSizes.inputBox
        )
        ui.addActor(pin)
        ui.addActor(pin.label)

        ui.addActor(joinButton)
        Gdx.input.inputProcessor = ui

        joinButton.onClick {
            controller.connectToGame(username = username.text, joinPin = pin.text)
        }
    }

    override fun render(delta: Float) {
        handleInput()
        batch.projectionMatrix = cam.combined
        batch.begin()
        batch.draw(
            background,
            backgroundPosition.x,
            backgroundPosition.y,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
        batch.draw(
            logo,
            logoPosition.x,
            logoPosition.y,
            TextureSizes.logo.width,
            TextureSizes.logo.height
        )
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
        batch.dispose()
    }

    private fun handleInput() {

    }
}
