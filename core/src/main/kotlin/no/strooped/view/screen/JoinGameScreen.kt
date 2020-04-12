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
    private val usernameLabelPosition = Vector2(
        usernamePosition.x + (TextureSizes.inputBox.width - TextureSizes.usernameLabel.width) * 0.5f,
        usernamePosition.y + TextureSizes.inputBox.height * 1.2f
    )
    private val pinLabelPosition = Vector2(
        pinPosition.x + (TextureSizes.inputBox.width - TextureSizes.pinLabel.width) * 0.5f,
        pinPosition.y + TextureSizes.inputBox.height * 1.2f
    )
    private val background: Texture = Texture("white.jpg")
    private val logo: Texture = Texture("Strooped1.png")
    private val joinButton: UIButton = UIButton(
        "joinGameButton",
        "Join game",
        joinButtonPosition,
        TextureSizes.joinButton
    )
    private val userLabel: Texture = Texture("Username1.png")
    private val pinLabel: Texture = Texture("Pin.png")
    private lateinit var username: TextFieldInput
    private lateinit var pin: TextFieldInput
    override fun hide() {}

    override fun show() {
        batch = SpriteBatch()
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

        username = TextFieldInput(
            "username",
            usernamePosition,
            TextureSizes.inputBox
        )
        ui.addActor(username)

        pin = TextFieldInput(
            "pin",
            pinPosition,
            TextureSizes.inputBox
        )
        ui.addActor(pin)
        ui.addActor(joinButton)
        Gdx.input.inputProcessor = ui
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
        batch.draw(
            userLabel,
            usernameLabelPosition.x,
            usernameLabelPosition.y,
            TextureSizes.usernameLabel.width,
            TextureSizes.usernameLabel.height
        )
        batch.draw(
            pinLabel,
            pinLabelPosition.x,
            pinLabelPosition.y,
            TextureSizes.pinLabel.width,
            TextureSizes.pinLabel.height
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
        if (joinButton.isPressed) {
            controller.connectToGame(username = username.text, joinPin = pin.text)
        }
    }
}
