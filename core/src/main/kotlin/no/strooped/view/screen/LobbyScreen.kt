package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.GameSingleton
import no.strooped.StroopedController
import no.strooped.TextureSizes
import no.strooped.model.GameRoom
import no.strooped.view.screen.components.Animation
import no.strooped.view.screen.components.UIButton
import java.lang.RuntimeException

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
private val FONT_SIZE_WELCOME_TEXT = TextureSizes.getScaledFontSize(3.0f)
class LobbyScreen(
    private val controller: StroopedController,
    private val gameRoom: GameRoom
) : Screen {
    private var ui: Stage = Stage(ScreenViewport())
    private lateinit var batch: SpriteBatch
    private val cam: OrthographicCamera = OrthographicCamera()
    private val backgroundPosition = Vector2(cam.position.x - cam.viewportWidth * 0.5f, 0f)
    private val exitButtonPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.exitGameButton.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.1f
    )
    private val loadSpinnerPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.loadSpinner.width) * 0.5f,
        (Gdx.graphics.height.toFloat() - TextureSizes.loadSpinner.height) * 0.35f
    )
    private val background: Texture = Texture("white.jpg")
    private val exitButton: UIButton = UIButton(
        "exitGameButton",
        "Exit game",
        exitButtonPosition,
        TextureSizes.exitGameButton
    )
    private lateinit var loadingAnimation: Animation

    private val numberOfFrames = 4
    private val refreshInterval = 0.1f
    init {
        print("Stuff")
        // this init will disappear in future versions
            /*Label label = new Label(labelText, skin);
Pixmap labelColor = new Pixmap(labelWidth, labelHeight, Pixmap.Format.RGB888);
labelColor.setColor(<your-color-goes-here>);
labelColor.fill();
label.getStyle().background = new Image(new Texture(labelColor)).getDrawable();*/
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
        val nameOfTexture = "load"
        loadingAnimation = Animation(nameOfTexture, numberOfFrames, refreshInterval)
        val message = "Hey " + gameRoom.player.username + "!\nPlease wait for the lobby-master to start " +
            "the game.\nWe made a spinning circle you can enjoy while you wait."
        val label = Label(message, Label.LabelStyle(BitmapFont(Gdx.files.internal("chunkfive.fnt")), Color.FIREBRICK))
        label.setFontScale(FONT_SIZE_WELCOME_TEXT)
        val labelWidth = Gdx.graphics.width * 0.8f
        label.width = labelWidth
        val labelPosX = (Gdx.graphics.width - label.width) * 0.5f
        val labelPosY = Gdx.graphics.height * 0.7f
        label.setPosition(labelPosX, labelPosY)
        label.setWrap(true) // fit inside the label
        label.setAlignment(1) // center the text
        ui.addActor(label)
        ui.addActor(exitButton)
        Gdx.input.inputProcessor = ui
        exitButton.onClick {
            controller.exitLobby()
        }
    }

    override fun render(delta: Float) {
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
            loadingAnimation.getTexture(Gdx.graphics.deltaTime),
            loadSpinnerPosition.x,
            loadSpinnerPosition.y,
            TextureSizes.loadSpinner.width,
            TextureSizes.loadSpinner.height)
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
}
