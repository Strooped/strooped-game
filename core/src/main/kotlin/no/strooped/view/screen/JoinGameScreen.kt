package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.TextureSizes
import no.strooped.view.screen.components.TextFieldInput

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
val FONT_SIZE_INPUT_FIELDS = TextureSizes.adjustedFontSize(2.0f)
class JoinGameScreen(private val controller: StroopedController) : Screen {
    var ui: Stage = Stage(ScreenViewport())
    lateinit var batch: SpriteBatch

    private val cam: OrthographicCamera = OrthographicCamera()
    private val background: Texture = Texture("white.jpg")
    private val logo: Texture = Texture("Strooped1.png")
    private val joinButton: Texture = Texture("join_game_button.png")
    private val userLabel: Texture = Texture("Username1.png")
    private val pinLabel: Texture = Texture("Pin.png")
    private val username: TextFieldInput
    private val pin: TextFieldInput
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

    init {
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
        Gdx.input.inputProcessor = ui
    }

    override fun hide() {}

    override fun show() {
        batch = SpriteBatch()
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
            joinButton,
            joinButtonPosition.x,
            joinButtonPosition.y,
            TextureSizes.joinButton.width,
            TextureSizes.joinButton.height
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
        if (Gdx.input.justTouched()) {
            val rec = Rectangle(
                joinButtonPosition.x,
                joinButtonPosition.y,
                TextureSizes.joinButton.width,
                TextureSizes.joinButton.height)
            val touchX = Gdx.input.x.toFloat()
            val touchY = cam.viewportHeight - Gdx.input.y
            if (rec.contains(Vector2(touchX, touchY))) {
                println("Touch")
                // gsm.set(PlayState(gsm))
                controller.connectToGame(username = username.text, joinPin = pin.text)
            }
        }
    }
}
