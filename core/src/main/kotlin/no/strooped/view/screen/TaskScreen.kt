package no.strooped.view.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport
import no.strooped.GameSingleton
import no.strooped.StroopedController
import no.strooped.TextureSizes
import no.strooped.model.Task
import no.strooped.view.screen.components.Answer
import no.strooped.view.screen.components.Button
import no.strooped.view.screen.components.ColorButton
import no.strooped.view.screen.components.Label

private val FONT_SIZE_LABEL_TEXT = TextureSizes.getScaledFontSize(3.0f)
class TaskScreen(
    private val controller: StroopedController,
    private val currentTask: Task
) : Screen {
    private var ui: Stage = Stage(ScreenViewport())
    private val background: Image = Image(Texture("white.jpg"))
    private var colorOptions: Array<ColorButton?> = arrayOfNulls(currentTask.buttons.size)
    private var colorClicked = false
    private val message = "Task ${GameSingleton.taskNumber}"
    private val labelWidth = Gdx.graphics.width * 0.8f
    private val backgroundPosition = Vector2(0f, 0f)
    private val labelPosition = Vector2(
        (Gdx.graphics.width - labelWidth) * 0.5f,
        Gdx.graphics.height * 0.85f
    )

    override fun hide() {}

    override fun show() {
        background.setPosition(backgroundPosition.x, backgroundPosition.y)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        ui.addActor(background)
        val label = Label(message, labelPosition, labelWidth, FONT_SIZE_LABEL_TEXT, Color.BLACK)
        ui.addActor(label)
        val stringOfColors = currentTask.buttons
        val colorPosition = Vector2(TextureSizes.distanceBetweenButtons, TextureSizes.distanceBetweenButtons)
        val colorButtonSize = TextureSizes.getColorButtonSize(colorOptions.size)
        for (i in colorOptions.indices) {
            colorOptions[i] = ColorButton("", colorPosition, colorButtonSize, stringOfColors[i])
            ui.addActor(colorOptions[i])
            if (i % 2 == 0) {
                colorPosition.x += colorButtonSize.width + TextureSizes.distanceBetweenButtons
            } else {
                colorPosition.y += colorButtonSize.height + TextureSizes.distanceBetweenButtons
                colorPosition.x = TextureSizes.distanceBetweenButtons
            }
        }
        colorOptions.forEach { button ->
            button!!.onClick {
                if (!colorClicked) {
                    focusOnButton(button)
                    println(button.getStringColor())
                    controller.answerTask(Answer(button.getStringColor(), System.currentTimeMillis()))
                }
                colorClicked = true
            }
        }
        Gdx.input.inputProcessor = ui
    }

    private fun focusOnButton(button: Button) {
        val otherColors = colorOptions.filter { it != button }.filterNotNull()

        otherColors.forEach {
            it.changeColor("#737373")
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
