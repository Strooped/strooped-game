package no.strooped.view.screen.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label

class Label(
    message: String,
    position: Vector2,
    width: Float,
    font: Float,
    color: Color = Color.WHITE
) : Label(message, LabelStyle(BitmapFont(Gdx.files.internal("applegothic.fnt")), color)) {
    init {
        setPosition(position.x, position.y)
        setWidth(width) // might need to move it from here
        setFontScale(font)
        setWrap(true) // fit inside the label
        setAlignment(1) // center the text
    }
}
