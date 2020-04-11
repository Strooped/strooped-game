package no.strooped.view.screen.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import no.strooped.TextureSizes
import no.strooped.util.Size

private fun buildStyles(): TextField.TextFieldStyle
{
    val nameOfSkin = "background"
    val inputBoxBackgroundSize = 10
    val patch = NinePatch(
        Texture("white.jpg"),
        inputBoxBackgroundSize,
        inputBoxBackgroundSize,
        inputBoxBackgroundSize,
        inputBoxBackgroundSize
    )
    val skin = Skin()
    val myStyle = TextField.TextFieldStyle()
    val fontSizeInputField = TextureSizes.adjustedFontSize(2.0f)
    skin.add(nameOfSkin, patch)
    myStyle.font = BitmapFont(Gdx.files.internal("chunkfive.fnt"))
    myStyle.fontColor = Color.BLACK
    myStyle.font.data.setScale(fontSizeInputField)
    myStyle.background = skin.getDrawable(nameOfSkin)
    return myStyle
}

class TextFieldInput(val nameOfField: String, position: Vector2, size: Size) : TextField("", buildStyles()) {
    init {
        super.setPosition(position.x, position.y)
        super.setSize(size.width, size.height)
    }
}
