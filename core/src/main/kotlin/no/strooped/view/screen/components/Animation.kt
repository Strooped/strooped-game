package no.strooped.view.screen.components

import com.badlogic.gdx.graphics.Texture

class Animation(
    private val nameOfTexture: String,
    private val numberOfFrames: Int,
    private val refreshInterval: Float
) {
    private val textures: Array<Texture?> = arrayOfNulls(numberOfFrames)
    private var iterator = 0
    private var elapsedTime = 0f
    init {
        for (i in 0 until numberOfFrames) {
            val nameOfImage = "$nameOfTexture$i.jpg"
            textures[i] = Texture(nameOfImage)
        }
    }
    fun getTexture(time: Float): Texture? {
        elapsedTime += time
        if (elapsedTime > refreshInterval) {
            iterator++
            elapsedTime = 0f
            if (iterator == numberOfFrames) {
                iterator = 0
            }
        }
        return textures[iterator]
    }
}
