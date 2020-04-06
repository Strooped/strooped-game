package com.mygdx.game.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import javax.xml.soap.Text

abstract class State protected constructor(protected var gsm: GameStateManager) {
    protected var cam: OrthographicCamera
    protected var mouse: Vector3
    protected var background: Texture
    protected var logo: Texture
    protected var joinBtn: Texture
    protected abstract fun handleInput()
    fun update(dt: Float) {
        handleInput()
        afterHandle(dt)
    }

    abstract fun render(sb: SpriteBatch?)
    abstract fun dispose()
    protected fun afterHandle(dt: Float) {}

    init {
        cam = OrthographicCamera()
        mouse = Vector3()
        background = Texture("white.jpg")
        logo = Texture("Strooped1.png")
        joinBtn = Texture("join_game_button.png")

    }
}
