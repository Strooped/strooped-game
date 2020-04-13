package no.strooped.view.screen.components

import com.badlogic.gdx.graphics.Colors
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import no.strooped.util.Size

private fun buildStyles(color: String): TextButton.TextButtonStyle {
    val myStyle = TextButton.TextButtonStyle()
    val buttonColor = Pixmap(50, 50, Pixmap.Format.RGB888)
    buttonColor.setColor(Colors.get(color))
    buttonColor.fill()
    myStyle.font = BitmapFont()
    myStyle.up = Image(Texture(buttonColor)).drawable
    return myStyle
}

class ColorButton(
    label: String,
    position: Vector2,
    size: Size,
    color: String
) : Button(label, buildStyles(color)) {
    init {
        setPosition(position.x, position.y)
        setSize(size.width, size.height)
    }
}
