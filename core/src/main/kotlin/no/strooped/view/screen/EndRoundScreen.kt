package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.GameSingleton
import no.strooped.TextureSizes
import no.strooped.view.screen.components.Label
import no.strooped.view.screen.components.PlacementLabel

private val FONT_SIZE_LABEL_TEXT = TextureSizes.getScaledFontSize(4.0f)
class EndRoundScreen : Screen {
    var ui: Stage = Stage(ScreenViewport())
    private val background: Image = Image(Texture("background.png"))
    private val backgroundPosition = Vector2(0f, 0f)
    private val labelWidth = Gdx.graphics.width * 0.8f
    private val labelPosition = Vector2(
        (Gdx.graphics.width - labelWidth) * 0.5f,
        Gdx.graphics.height * 0.8f
    )
    private val placementPosition = Vector2(
        (Gdx.graphics.width - labelWidth) * 0.5f,
        Gdx.graphics.height * 0.55f
    )
    private val supportPosition = Vector2(
        (Gdx.graphics.width - labelWidth) * 0.5f,
        Gdx.graphics.height * 0.2f
    )
    override fun hide() {}

    override fun show() {
        background.setPosition(backgroundPosition.x, backgroundPosition.y)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        ui.addActor(background)
        val message = "You are currently in"
        val label = Label(message, labelPosition, labelWidth, FONT_SIZE_LABEL_TEXT)
        ui.addActor(label)
        val placement = GameSingleton.room?.player?.placement
        val placementLabel = PlacementLabel(
            placement,
            placementPosition,
            labelWidth,
            FONT_SIZE_LABEL_TEXT
        )
        ui.addActor(placementLabel)
        val supportLabel = Label(placementLabel.supportMessage(), supportPosition, labelWidth, FONT_SIZE_LABEL_TEXT)
        ui.addActor(supportLabel)
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
