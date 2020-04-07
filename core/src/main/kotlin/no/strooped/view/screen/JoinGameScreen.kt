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
import no.strooped.StroopedController

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
class JoinGameScreen(val controller: StroopedController) : Screen {
    var ui: Stage = Stage(ScreenViewport())

    protected var cam: OrthographicCamera
    protected var mouse: Vector3
    protected var background: Texture
    protected var logo: Texture
    protected var joinBtn: Texture
//    private lateinit var stage: Stage

    lateinit var batch: SpriteBatch

    init {
        cam = OrthographicCamera()
        mouse = Vector3()
        background = Texture("white.jpg")
        logo = Texture("Strooped1.png")
        joinBtn = Texture("join_game_button.png")

        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        val temp = NinePatch(Texture("white.jpg"), 10, 10, 10, 10)
        val skin = Skin()
        skin.add("background", temp)
//        stage = Stage()
        val style = TextField.TextFieldStyle()
        style.fontColor = Color.BLACK
        style.font = BitmapFont()
        style.font.data.setScale(5.0f)
        style.background = skin.getDrawable("background");

        val username = TextField("", style)
        username.setPosition(Gdx.graphics.width.toFloat() * 0.15f, Gdx.graphics.height.toFloat() * 0.5f)
        ui.addActor(username)
        username.setSize(Gdx.graphics.width.toFloat() * 0.7f, Gdx.graphics.height.toFloat() * 0.05f)
        Gdx.input.inputProcessor = ui
        val label = Label("Username", Label.LabelStyle(BitmapFont(), Color.BLACK))
        label.setFontScale(username.height / 25)
        label.width = label.width * (username.height / 25f)
        label.setPosition(username.x + username.width / 2f - label.width / 2f, username.y + username.height * 1.5f)
        ui.addActor(label)

        val pin = TextField("", style)
        pin.setPosition(Gdx.graphics.width.toFloat() * 0.15f, Gdx.graphics.height.toFloat() * 0.4f)
        ui.addActor(pin)
        pin.setSize(Gdx.graphics.width.toFloat() * 0.7f, Gdx.graphics.height.toFloat() * 0.05f)
        Gdx.input.inputProcessor = ui
        val label2 = Label("Pin", Label.LabelStyle(BitmapFont(), Color.BLACK))
        label2.setFontScale(pin.height / 25)
        label2.width = label2.width * (pin.height / 25f)
        label2.setPosition(pin.x + pin.width / 2f - label2.width / 2f, pin.y + pin.height * 1.5f)
        ui.addActor(label2)
    }

    override fun hide() {
    }

    override fun show() {
        batch = SpriteBatch()
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        handleInput()

        batch.projectionMatrix = cam.combined
        val logWidth = logo.width * 2f
        val logHeight = logo.height * 2f
        batch.begin()
        batch.draw(background, cam.position.x - cam.viewportWidth / 2, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.draw(logo, Gdx.graphics.width.toFloat() * 0.5f - logWidth / 2, Gdx.graphics.height.toFloat() * 0.75f - logHeight / 2f, logWidth, logHeight)
        batch.draw(joinBtn, Gdx.graphics.width.toFloat() * 0.5f - joinBtn.width / 2f, Gdx.graphics.height.toFloat() * 0.3f)
        batch.end()
        ui.draw()

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
//        ui = null;
    }

    fun handleInput() {
        if (Gdx.input.justTouched()) {
            val rec = Rectangle(Gdx.graphics.width.toFloat() * 0.5f - joinBtn.width / 2f, Gdx.graphics.height.toFloat() * 0.3f, joinBtn.width + 0f, joinBtn.height + 0f)
            val touchX = Gdx.input.x.toFloat()
            val touchY = cam.viewportHeight - Gdx.input.y
            if (rec.contains(Vector2(touchX, touchY))) {
                /*perform a check does the game exist (using the pin) and is the username available)
                check how to write MVP*/
                //gsm.set(PlayState(gsm))
            }
        }
    }
}
