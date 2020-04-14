package no.strooped.view.screen.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import no.strooped.TextureSizes
import no.strooped.util.Size

private fun buildStyle(): TextButton.TextButtonStyle {
    val nameOfSkin = "background"
    val patch = NinePatch(Texture("button.png"))
    val skin = Skin()
    val myStyle = TextButton.TextButtonStyle()
    val fontSizeInputField = TextureSizes.getScaledFontSize(2.0f)
    skin.add(nameOfSkin, patch)
    myStyle.font = BitmapFont(Gdx.files.internal("applegothic.fnt"))
    myStyle.fontColor = Color.WHITE
    myStyle.font.data.setScale(fontSizeInputField)
    myStyle.up = skin.getDrawable(nameOfSkin)
    return myStyle
}
class UIButton(
    label: String,
    textToBeDisplayed: String,
    position: Vector2,
    size: Size
) : Button(label, buildStyle()) {
    init {
        setText(textToBeDisplayed)
        getLabel().setWrap(true)
        setPosition(position.x, position.y)
        setSize(size.width, size.height)
    }
}
