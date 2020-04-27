package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.singleton.TextureSizes
import no.strooped.view.components.Label
import no.strooped.view.components.TextFieldInput
import no.strooped.view.components.UIButton

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
class JoinGameScreen(
    private val controller: StroopedController,
    private val errorMessage: String? = null
) : Screen {

    var ui: Stage = Stage(ScreenViewport())
    private val backgroundPosition = Vector2(0f, 0f)
    private val logoPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.logo.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.85f - TextureSizes.logo.height * 0.5f
    )
    private val joinButtonPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.joinButton.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.20f
    )
    private val usernamePosition = Vector2(Gdx.graphics.width * 0.15f, Gdx.graphics.height * 0.56f)
    private val pinPosition = Vector2(Gdx.graphics.width * 0.15f, Gdx.graphics.height * 0.44f)
    private val errorPosition = Vector2(
        Gdx.graphics.width * 0.05f,
        joinButtonPosition.y - 2 * TextureSizes.joinButton.height
    )
    private val background: Image = Image(Texture("background.png"))
    private val logo: Image = Image(Texture("logo.png"))
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
        background.setPosition(backgroundPosition.x, backgroundPosition.y)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        ui.addActor(background)

        logo.setPosition(logoPosition.x, logoPosition.y)
        logo.setSize(TextureSizes.logo.width, TextureSizes.logo.height)
        ui.addActor(logo)
        username = TextFieldInput(
            "Username",
            usernamePosition,
            TextureSizes.inputBox
        )
        ui.addActor(username)
        ui.addActor(username.label)

        pin = TextFieldInput(
            "Game PIN",
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

        errorMessage?.let {
            val errorMessage = Label(it, errorPosition, Gdx.graphics.width * 0.9f, TextureSizes.getScaledFontSize(2.0f))
            ui.addActor(errorMessage)
        }
    }

    override fun render(delta: Float) {
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
}
