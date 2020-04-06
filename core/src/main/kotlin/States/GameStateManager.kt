package com.mygdx.game.states

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.*

class GameStateManager {
    private val states: Stack<State>
    fun push(state: State) {
        states.push(state)
    }

    fun pop() {
        states.pop().dispose()
    }

    fun set(state: State) {
        states.pop().dispose()
        states.push(state)
    }

    fun update(dt: Float) {
        states.peek().update(dt)
    }

    fun render(sb: SpriteBatch?) {
        states.peek().render(sb)
    }

    init {
        states = Stack<State>()
    }
}
