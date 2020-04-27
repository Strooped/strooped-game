package no.strooped.view.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import no.strooped.model.ColorOption
import no.strooped.singleton.TextureSizes
import no.strooped.util.Size
private val FONT_SIZE = TextureSizes.getScaledFontSize(1.5f)

private fun getPixmapRoundedRectangle(width: Int, height: Int, radius: Int, color: Color): Pixmap? {
    val pixmap = Pixmap(width, height, Pixmap.Format.RGBA8888)
    pixmap.setColor(color)

    // Pink rectangle
    pixmap.fillRectangle(0, radius, pixmap.width, pixmap.height - 2 * radius)

    // Green rectangle
    pixmap.fillRectangle(radius, 0, pixmap.width - 2 * radius, pixmap.height)

    // Bottom-left circle
    pixmap.fillCircle(radius, radius, radius)

    // Top-left circle
    pixmap.fillCircle(radius, pixmap.height - radius, radius)

    // Bottom-right circle
    pixmap.fillCircle(pixmap.width - radius, radius, radius)

    // Top-right circle
    pixmap.fillCircle(pixmap.width - radius, pixmap.height - radius, radius)
    return pixmap
}

private fun buildStyles(color: String?, fontColor: String, size: Size): TextButton.TextButtonStyle {
    val myStyle = TextButton.TextButtonStyle()
    val buttonPixmap = getPixmapRoundedRectangle(
        size.width.toInt(),
        size.height.toInt(),
        30,
        Color.valueOf(color)
    )
    myStyle.font = BitmapFont(Gdx.files.internal("applegothic.fnt"))
    myStyle.font.data.setScale(FONT_SIZE)
    myStyle.fontColor = Color.valueOf(fontColor)
    myStyle.up = Image(Texture(buttonPixmap)).drawable
    return myStyle
}

class ColorButton(
    private val colorOption: ColorOption,
    position: Vector2,
    size: Size
) : Button(colorOption.name, buildStyles(colorOption.backgroundColor, colorOption.fontColor, size)) {
    init {
        setPosition(position.x, position.y)
        setSize(size.width, size.height)
        setText(colorOption.name)
    }
    fun changeColor(color: String) {
        setColor(Color.valueOf(color))
    }
    fun getAnswer(): String? {
        return colorOption.color
    }
}
