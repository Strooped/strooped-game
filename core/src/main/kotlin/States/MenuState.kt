package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.NinePatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextField
import java.io.OutputStream


class MenuState(game: GameStateManager?) : State(game!!) {
    //private val playBtn: Texture
    //private val startText: Texture
    private lateinit var stage: Stage
    public override fun handleInput() {
        if (Gdx.input.justTouched()) {
            val rec = Rectangle(Gdx.graphics.width.toFloat() * 0.5f - joinBtn.width / 2f, Gdx.graphics.height.toFloat() * 0.3f, joinBtn.width + 0f, joinBtn.height + 0f)
            val touchX = Gdx.input.x.toFloat()
            val touchY = cam.viewportHeight - Gdx.input.y
            if (rec.contains(Vector2(touchX, touchY))) {
                /*perform a check does the game exist (using the pin) and is the username available)
                check how to write MVP*/
                //gsm.set(PlayState(gsm))
            }
        }
    }

    override fun render(sb: SpriteBatch?) {
        sb!!.projectionMatrix = cam.combined
        val logWidth = logo.width * 2f
        val logHeight = logo.height * 2f
        sb.begin()
        sb.draw(background, cam.position.x - cam.viewportWidth / 2, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        sb.draw(logo, Gdx.graphics.width.toFloat() * 0.5f - logWidth / 2, Gdx.graphics.height.toFloat() * 0.75f - logHeight / 2f, logWidth, logHeight)
        sb.draw(joinBtn, Gdx.graphics.width.toFloat() * 0.5f - joinBtn.width / 2f, Gdx.graphics.height.toFloat() * 0.3f)
        sb.end()
        stage.draw()
    }

    override fun dispose() {
        joinBtn.dispose()
        logo.dispose()
    }

    init {
        cam.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        val temp = NinePatch(Texture("white.jpg"), 10, 10, 10, 10)
        val skin = Skin()
        skin.add("background", temp)
        stage = Stage()
        val style = TextField.TextFieldStyle()
        style.fontColor = Color.BLACK
        style.font = BitmapFont()
        style.font.data.setScale(5.0f)
        style.background = skin.getDrawable("background");

        val username = TextField("", style)
        username.setPosition(Gdx.graphics.width.toFloat() * 0.15f, Gdx.graphics.height.toFloat() * 0.5f)
        stage.addActor(username)
        username.setSize(Gdx.graphics.width.toFloat() * 0.7f, Gdx.graphics.height.toFloat() * 0.05f)
        Gdx.input.inputProcessor = stage;
        val label = Label("Username", LabelStyle(BitmapFont(), Color.BLACK))
        label.setFontScale(username.height / 25)
        label.width = label.width * (username.height / 25f)
        label.setPosition(username.x + username.width / 2f - label.width / 2f, username.y + username.height * 1.5f)
        stage.addActor(label)

        val pin = TextField("", style)
        pin.setPosition(Gdx.graphics.width.toFloat() * 0.15f, Gdx.graphics.height.toFloat() * 0.4f)
        stage.addActor(pin)
        pin.setSize(Gdx.graphics.width.toFloat() * 0.7f, Gdx.graphics.height.toFloat() * 0.05f)
        Gdx.input.inputProcessor = stage;
        val label2 = Label("Pin", LabelStyle(BitmapFont(), Color.BLACK))
        label2.setFontScale(pin.height / 25)
        label2.width = label2.width * (pin.height / 25f)
        label2.setPosition(pin.x + pin.width / 2f - label2.width / 2f, pin.y + pin.height * 1.5f)
        stage.addActor(label2)
    }
}
