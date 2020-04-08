package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.sun.org.apache.xpath.internal.operations.Or
import no.strooped.StroopedController
import no.strooped.TextureSizes

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
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

    init {
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        val temp = NinePatch(Texture("white.jpg"), 10, 10, 10, 10)
        val skin = Skin()
        skin.add("background", temp)
        style = TextField.TextFieldStyle()
        style.fontColor = Color.BLACK
        style.font = BitmapFont()
        style.font.data.setScale(5.0f)
        style.background = skin.getDrawable("background");

        username = TextField("", style)
        username.setPosition(Gdx.graphics.width.toFloat() * 0.15f, Gdx.graphics.height.toFloat() * 0.5f)
        ui.addActor(username)
        username.setSize(TextureSizes.inputBoxWidth(), TextureSizes.inputBoxHeight())
        Gdx.input.inputProcessor = ui

        pin = TextField("", style)
        pin.setPosition(Gdx.graphics.width.toFloat() * 0.15f, Gdx.graphics.height.toFloat() * 0.38f)
        ui.addActor(pin)
        pin.setSize(TextureSizes.inputBoxWidth(), TextureSizes.inputBoxHeight())
        Gdx.input.inputProcessor = ui
    }

    override fun hide() {
    }

    override fun show() {
        batch = SpriteBatch()
    }

    override fun render(delta: Float) {
        handleInput()

        batch.projectionMatrix = cam.combined
        batch.begin()
        batch.draw(background, cam.position.x - cam.viewportWidth / 2, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.draw(logo, (Gdx.graphics.width.toFloat() - TextureSizes.logoWidth()) * 0.5f, Gdx.graphics.height.toFloat() * 0.75f - TextureSizes.logoHeight() * 0.5f,  TextureSizes.logoWidth(), TextureSizes.logoHeight())
        batch.draw(joinBtn, (Gdx.graphics.width.toFloat() - TextureSizes.joinGameButtonWidth()) * 0.5f, Gdx.graphics.height.toFloat() * 0.27f, TextureSizes.joinGameButtonWidth(), TextureSizes.joinGameButtonHeight())
        batch.draw(userLabel, username.x + (TextureSizes.inputBoxWidth() - TextureSizes.userLabelWidth()) * 0.5f, username.y + TextureSizes.inputBoxHeight() * 1.2f, TextureSizes.userLabelWidth(), TextureSizes.userLabelHeight())
        batch.draw(pinLabel, pin.x + (TextureSizes.inputBoxWidth() - TextureSizes.pinLabelWidth()) * 0.5f, pin.y + TextureSizes.inputBoxHeight() * 1.2f, TextureSizes.pinLabelWidth(), TextureSizes.pinLabelHeight())
        batch.end()
        ui.draw()
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
        ui.viewport?.update(width, height);
    }

    override fun dispose() {
        ui.dispose()
    }

    fun handleInput() {
        if (Gdx.input.justTouched()) {
            val rec = Rectangle((Gdx.graphics.width.toFloat() - TextureSizes.joinGameButtonWidth()) * 0.5f, Gdx.graphics.height.toFloat() * 0.27f, TextureSizes.joinGameButtonWidth(), TextureSizes.joinGameButtonHeight())
            val touchX = Gdx.input.x.toFloat()
            val touchY = cam.viewportHeight - Gdx.input.y
            if (rec.contains(Vector2(touchX, touchY))) {
                System.out.println("Touch")
                //gsm.set(PlayState(gsm))
            }
        }
    }
}
