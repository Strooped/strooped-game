package no.strooped.view.screen.components

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.TextButton

open class Button(val label: String, buttonStyle: TextButtonStyle) : TextButton(label, buttonStyle) {
    fun onClick(callback: (InputEvent) -> Unit) {
        addListener(object : InputListener() {
            override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                callback(event)
                return super.touchDown(event, x, y, pointer, button)
            }
        })
    }
}
