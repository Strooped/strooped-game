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
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.GameSingleton
import no.strooped.StroopedController
import no.strooped.TextureSizes
import no.strooped.model.GameRoom
import no.strooped.view.screen.components.ColorButton
import no.strooped.view.screen.components.UIButton

/**
 * Uses https://otter.tech/an-mvc-guide-for-libgdx/ as inspiration
 * */
private val FONT_SIZE_WELCOME_TEXT = TextureSizes.getScaledFontSize(3.0f)
class TaskScreen(
    private val controller: StroopedController,
    private val gameRoom: GameRoom
) : Screen {
    private var ui: Stage = Stage(ScreenViewport())
    private lateinit var batch: SpriteBatch
    private val cam: OrthographicCamera = OrthographicCamera()
    private val backgroundPosition = Vector2(0f, 0f)
    private val exitButtonPosition = Vector2(
        (Gdx.graphics.width.toFloat() - TextureSizes.exitGameButton.width) * 0.5f,
        Gdx.graphics.height.toFloat() * 0.1f
    )
    private val background: Image = Image(Texture("white.jpg"))
    private val exitButton: UIButton = UIButton(
        "exitGameButton",
        "Exit game",
        exitButtonPosition,
        TextureSizes.exitGameButton
    )
    private var colorOptions: Array<ColorButton?> = arrayOfNulls(gameRoom.currentTask!!.possibleAnswers.size)
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
        background.setPosition(backgroundPosition.x, backgroundPosition.y)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        ui.addActor(background)
        val message = "Task 1"
        val label = Label(message, Label.LabelStyle(BitmapFont(Gdx.files.internal("chunkfive.fnt")), Color.FIREBRICK))
        label.setFontScale(FONT_SIZE_WELCOME_TEXT)
        val labelWidth = Gdx.graphics.width * 0.8f
        label.width = labelWidth
        val labelPosition = Vector2(
            (Gdx.graphics.width - label.width) * 0.5f,
            Gdx.graphics.height * 0.7f
        )
        label.setPosition(labelPosition.x, labelPosition.y)
        label.setWrap(true) // fit inside the label
        label.setAlignment(1) // center the text
        ui.addActor(label)
        ui.addActor(exitButton)
        val stringOfColors = GameSingleton.room!!.currentTask!!.possibleAnswers
        for (i in 0 until colorOptions.size) {
            println("Size" + colorOptions.size)
            val colorPosition = Vector2(100f * i, 100f * i)
            colorOptions[i] = ColorButton("", colorPosition, TextureSizes.colorButton, stringOfColors[i])
            ui.addActor(colorOptions[i])
        }
        Gdx.input.inputProcessor = ui
        exitButton.onClick {
            controller.exitLobby()
        }
    }

    override fun render(delta: Float) {
        batch.projectionMatrix = cam.combined
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

