package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.TextureSizes

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
private val FONT_SIZE_INPUT_FIELDS = TextureSizes.adjustedFontSize(2.0f)
const val NAME_OF_SKIN = "background"
const val TEMP_SIZE = 10

class JoinGameScreen(val controller: StroopedController) : Screen {
    var ui: Stage = Stage(ScreenViewport())

    var cam: OrthographicCamera = OrthographicCamera()
    var mouse: Vector3 = Vector3()
    var background: Texture = Texture("white.jpg")
    var logo: Texture = Texture("Strooped1.png")
    var joinBtn: Texture = Texture("join_game_button.png")
    var userLabel: Texture = Texture("Username1.png")
    var pinLabel: Texture = Texture("Pin.png")
    var style: TextField.TextFieldStyle
    var username: TextField
    var pin: TextField
    lateinit var batch: SpriteBatch

    val joinBtnPosX = (Gdx.graphics.width.toFloat() - TextureSizes.joinGameButtonWidth()) * 0.5f
    val joinBtnPosY = Gdx.graphics.height.toFloat() * 0.27f

    init {
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        val temp = NinePatch(Texture("white.jpg"), TEMP_SIZE, TEMP_SIZE, TEMP_SIZE, TEMP_SIZE)
        val skin = Skin()
        skin.add(NAME_OF_SKIN, temp)
        style = TextField.TextFieldStyle()
        style.font = BitmapFont(Gdx.files.internal("chunkfive.fnt"))
        style.fontColor = Color.BLACK
        // style.font = BitmapFont()
        style.font.data.setScale(FONT_SIZE_INPUT_FIELDS)
        style.background = skin.getDrawable(NAME_OF_SKIN)

        @Suppress("MagicNumber")
        val userPositionX = Gdx.graphics.width.toFloat() * 0.15f
        @Suppress("MagicNumber")
        val userPositionY = Gdx.graphics.height.toFloat() * 0.5f
        username = TextField("", style)
        username.setPosition(userPositionX, userPositionY)
        ui.addActor(username)
        username.setSize(TextureSizes.inputBoxWidth(), TextureSizes.inputBoxHeight())
        Gdx.input.inputProcessor = ui

        @Suppress("MagicNumber")
        val pinPositionX = Gdx.graphics.width.toFloat() * 0.15f
        @Suppress("MagicNumber")
        val pinPositionY = Gdx.graphics.height.toFloat() * 0.38f
        pin = TextField("", style)
        pin.setPosition(pinPositionX, pinPositionY)
        ui.addActor(pin)
        pin.setSize(TextureSizes.inputBoxWidth(), TextureSizes.inputBoxHeight())
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
        val backPosX = cam.position.x - cam.viewportWidth / 2f
        val backPosY = 0f
        batch.draw(
            background,
            backPosX,
            backPosY,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
        val logoPosX = (Gdx.graphics.width.toFloat() - TextureSizes.logoWidth()) * 0.5f
        val logoPosY = Gdx.graphics.height.toFloat() * 0.75f - TextureSizes.logoHeight() * 0.5f
        batch.draw(
            logo,
            logoPosX,
            logoPosY,
            TextureSizes.logoWidth(),
            TextureSizes.logoHeight()
        )
        batch.draw(
            joinBtn,
            joinBtnPosX,
            joinBtnPosY,
            TextureSizes.joinGameButtonWidth(),
            TextureSizes.joinGameButtonHeight()
        )
        val userLabelPosX = username.x + (TextureSizes.inputBoxWidth() - TextureSizes.userLabelWidth()) * 0.5f
        val userLabelPosY = username.y + TextureSizes.inputBoxHeight() * 1.2f
        batch.draw(
            userLabel,
            userLabelPosX,
            userLabelPosY,
            TextureSizes.userLabelWidth(),
            TextureSizes.userLabelHeight()
        )
        val pinLabelPosX = pin.x + (TextureSizes.inputBoxWidth() - TextureSizes.pinLabelWidth()) * 0.5f
        val pinLabelPosY = pin.y + TextureSizes.inputBoxHeight() * 1.2f
        batch.draw(
            pinLabel,
            pinLabelPosX,
            pinLabelPosY,
            TextureSizes.pinLabelWidth(),
            TextureSizes.pinLabelHeight()
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
                joinBtnPosX,
                joinBtnPosY,
                TextureSizes.joinGameButtonWidth(),
                TextureSizes.joinGameButtonHeight())
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
