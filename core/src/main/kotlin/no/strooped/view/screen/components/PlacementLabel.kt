package no.strooped.view.screen.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label

class PlacementLabel(
    val placement: Int,
    position: Vector2,
    width: Float,
    font: Float
) : Label("", LabelStyle(BitmapFont(Gdx.files.internal("applegothic.fnt")), Color.WHITE)) {
    init {
        var textToDisplay = placement.toString()
        @Suppress("MagicNumber") run {
            color = when (placement) {
                1 -> Color.GOLD
                2 -> Color.LIGHT_GRAY
                3 -> Color.BROWN
                else -> {
                    Color.RED
                }
            }
            textToDisplay += if (placement > 10 && ((placement % 10) in (1..3)) && ((placement / 10) % 10 == 1)) {
                "th"
            } else if (placement % 10 == 1) {
                "st"
            } else if (placement % 10 == 2) {
                "nd"
            } else if (placement % 10 == 3) {
                "rd"
            } else {
                "th"
            }
        }
        textToDisplay += " place"
        setText(textToDisplay)
        setPosition(position.x, position.y)
        setWidth(width) // might need to move it from here
        setFontScale(font)
        setWrap(true) // fit inside the label
        setAlignment(1) // center the text
    }
    fun supportMessage(): String {
        @Suppress("MagicNumber")
        return if (placement in (1..3)) {
            "Good job! Keep up the good work."
        } else {
            "You are falling behind! Step up your game."
        }
    }
}
