package no.strooped.view.components

import com.badlogic.gdx.graphics.Texture

class Animation(
    private val nameOfTexture: String,
    private val numberOfFrames: Int,
    private val refreshInterval: Float
) {
    private val textures: Array<Texture> = (0 until numberOfFrames).map {
        Texture("$nameOfTexture-$it.png")
    }.toTypedArray()
    private var currentFrame = 0
    private var elapsedTime = 0f
    fun getTexture(time: Float): Texture? {
        elapsedTime += time
        if (elapsedTime > refreshInterval) {
            currentFrame++
            elapsedTime = 0f
            if (currentFrame == numberOfFrames) {
                currentFrame = 0
            }
        }
        return textures[currentFrame]
    }
}
