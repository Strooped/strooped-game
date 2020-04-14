package no.strooped.view.screen.components

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import no.strooped.TextureSizes
import no.strooped.util.Size

private fun buildTextFieldStyle(): TextField.TextFieldStyle {
    val nameOfSkin = "background"
    val inputBoxBackgroundSize = 5
    val patch = NinePatch(
        Texture("white.jpg"),
        inputBoxBackgroundSize,
        inputBoxBackgroundSize,
        inputBoxBackgroundSize,
        inputBoxBackgroundSize
    )
    val skin = Skin()
    val myStyle = TextField.TextFieldStyle()
    val fontSizeInputField = TextureSizes.getScaledFontSize(2.0f)
    skin.add(nameOfSkin, patch)
    myStyle.font = BitmapFont(Gdx.files.internal("applegothic.fnt"))
    myStyle.fontColor = Color.BLACK
    myStyle.font.data.setScale(fontSizeInputField)
    myStyle.background = skin.getDrawable(nameOfSkin)
    return myStyle
}

private fun buildLabelStyle(): Label.LabelStyle {
    val myStyle = Label.LabelStyle()
    val fontSizeInputField = TextureSizes.getScaledFontSize(2.0f)
    myStyle.font = BitmapFont(Gdx.files.internal("applegothic.fnt"))
    myStyle.fontColor = Color.WHITE
    myStyle.font.data.setScale(fontSizeInputField)
    return myStyle
}

class TextFieldInput(nameOfField: String, position: Vector2, size: Size) : TextField("", buildTextFieldStyle()) {
    var label: Label = Label(nameOfField, buildLabelStyle())
    init {
        super.setPosition(position.x, position.y)
        super.setSize(size.width, size.height)
        val labelPosition = Vector2(
            position.x + (TextureSizes.inputBox.width - label.width) * 0.5f,
            position.y + TextureSizes.inputBox.height
        )
        label.setPosition(labelPosition.x, labelPosition.y)
    }
}
