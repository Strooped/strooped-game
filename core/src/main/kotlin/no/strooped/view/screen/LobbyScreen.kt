package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.StroopedController
import no.strooped.TextureSizes
import no.strooped.view.screen.components.Animation
import no.strooped.view.screen.components.Label
import no.strooped.view.screen.components.UIButton
/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
private val FONT_SIZE_WELCOME_TEXT = TextureSizes.getScaledFontSize(2.5f)
class LobbyScreen(private val controller: StroopedController) : Screen {
    private var ui: Stage = Stage(ScreenViewport())
    private lateinit var batch: SpriteBatch
    private val cam: OrthographicCamera = OrthographicCamera()
    private val backgroundPosition = Vector2(0f, 0f)
    private val exitButtonPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.exitGameButton.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.1f
    )
    private val loadSpinnerPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.loadSpinner.width) * 0.5f,
        (Gdx.graphics.height.toFloat() - TextureSizes.loadSpinner.height) * 0.35f
    )
    private val background: Image = Image(Texture("background.png"))
    private val exitButton: UIButton = UIButton(
        "exitGameButton",
        "Leave game",
        exitButtonPosition,
        TextureSizes.exitGameButton
    )
    private lateinit var loadingAnimation: Animation

    private val numberOfFrames = 31
    private val refreshInterval = 0.1f
    init {
        print("Stuff")
        // TO DO for later
        /*val toastFactory: Toast.ToastFactory = Builder()
            .font(attr.font)
            .build()*/
        // https://github.com/wentsa/Toast-LibGDX - for toasts
    }

    override fun hide() {}

    override fun show() {
        batch = SpriteBatch()
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch = SpriteBatch()
        background.setPosition(backgroundPosition.x, backgroundPosition.y)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        ui.addActor(background)
        val nameOfTexture = "loadingFrames/frame"
        loadingAnimation = Animation(nameOfTexture, numberOfFrames, refreshInterval)
        val message = "Waiting for game master to start the game"
        val labelWidth = Gdx.graphics.width * 0.9f
        val labelPosition = Vector2(
            (Gdx.graphics.width - labelWidth) * 0.5f,
            Gdx.graphics.height * 0.7f
        )
        val label = Label(message, labelPosition, labelWidth, FONT_SIZE_WELCOME_TEXT)
        ui.addActor(label)
        ui.addActor(exitButton)
        Gdx.input.inputProcessor = ui
        exitButton.onClick {
            controller.openJoinScreen()
        }
    }

    override fun render(delta: Float) {
        batch.projectionMatrix = cam.combined
        ui.draw()
        batch.begin()
        batch.draw(
            loadingAnimation.getTexture(Gdx.graphics.deltaTime),
            loadSpinnerPosition.x,
            loadSpinnerPosition.y,
            TextureSizes.loadSpinner.width,
            TextureSizes.loadSpinner.height)
        batch.end()
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
}
