package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.TextureSizes

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
const val FONT_SIZE_WELCOME_TEXT = 3.0f // needs calibration
class LobbyScreen(val controller: StroopedController) : Screen {
    var ui: Stage = Stage(ScreenViewport())

    var cam: OrthographicCamera = OrthographicCamera()
    var mouse: Vector3 = Vector3()
    var background: Texture = Texture("white.jpg")
    var exitBtn: Texture = Texture("exit_game_button.png")
    var loadAnim: Array<Texture?>

    var elapsedTime = 0f
    private lateinit var batch: SpriteBatch

    private var iterat = 0
    private val numberOfFrames = 4

    private val backPosX = cam.position.x - cam.viewportWidth / 2f
    private val backPosY = 0f

    private val exitBtnPosX = (Gdx.graphics.width.toFloat() - TextureSizes.exitGameButtonWidth()) * 0.5f
    private val exitBtnPosY = Gdx.graphics.height.toFloat() * 0.1f

    private val loadPosX = (Gdx.graphics.width.toFloat() - TextureSizes.loadWidth()) * 0.5f
    private val loadPosY = (Gdx.graphics.height.toFloat() - TextureSizes.loadHeight()) * 0.35f
    private val refreshInterval = 0.1f

    init {
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch = SpriteBatch()

        loadAnim = arrayOfNulls(numberOfFrames)
        for (i in 0 until numberOfFrames) {
            val nameOfImage = "load$i.jpg"
            loadAnim[i] = Texture(nameOfImage)
        }
        val messsage = "Hey " + controller.username + "!\nPlease wait for the lobby-master to start " +
            "the game.\nWe made a spinning circle you can enjoy while you wait."
        val label = Label(messsage, Label.LabelStyle(BitmapFont(Gdx.files.internal("chunkfive.fnt")), Color.FIREBRICK))
        label.setFontScale(FONT_SIZE_WELCOME_TEXT)
        val labelWidth = Gdx.graphics.width * 0.8f
        label.width = labelWidth
        val labelPosX = (Gdx.graphics.width - label.width) * 0.5f
        val labelPosY = Gdx.graphics.height * 0.7f
        label.setPosition(labelPosX, labelPosY)
        label.setWrap(true) // fit inside the label
        label.setAlignment(1) // center the text
        ui.addActor(label)

        /*val toastFactory: Toast.ToastFactory = Builder()
            .font(attr.font)
            .build()*/
        // https://github.com/wentsa/Toast-LibGDX - for toasts
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
        batch.draw(
            background,
            backPosX,
            backPosY,
            Gdx.graphics.width.toFloat(),
            Gdx.graphics.height.toFloat()
        )
        batch.draw(
            exitBtn,
            exitBtnPosX,
            exitBtnPosY,
            TextureSizes.exitGameButtonWidth(),
            TextureSizes.exitGameButtonHeight()
        )
        if (elapsedTime > refreshInterval) {
            iterat++
            elapsedTime = 0f
            if (iterat == numberOfFrames) {
                iterat = 0
            }
        }
        batch.draw(
            loadAnim[iterat],
            loadPosX,
            loadPosY,
            TextureSizes.loadWidth(),
            TextureSizes.loadHeight())
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
                exitBtnPosX,
                exitBtnPosY,
                TextureSizes.exitGameButtonWidth(),
                TextureSizes.exitGameButtonHeight())
            val touchX = Gdx.input.x.toFloat()
            val touchY = cam.viewportHeight - Gdx.input.y
            if (rec.contains(Vector2(touchX, touchY))) {
                controller.exitLobby()
            }
        }
    }
}
