package no.strooped.singleton

import com.badlogic.gdx.Gdx
import no.strooped.util.Size

object TextureSizes {
    private val testedScreen = Size(1080f, 2040f)
    val logo = Size(
        2 * Gdx.graphics.width * (291f / testedScreen.width),
        2 * Gdx.graphics.height * (56f / testedScreen.height)
    )
    val joinButton = Size(
        Gdx.graphics.width * (650f / testedScreen.width),
        Gdx.graphics.height * (133f / testedScreen.height)
    )
    val inputBox = Size(
        Gdx.graphics.width * 0.7f,
        Gdx.graphics.height * 0.06f
    )
    val loadSpinner = Size(
        Gdx.graphics.width * (500f / testedScreen.width),
        Gdx.graphics.height * (500f / testedScreen.height)
    )
    val exitGameButton = Size(
        Gdx.graphics.width * (650f / testedScreen.width),
        Gdx.graphics.height * (133f / testedScreen.height)
    )
    val smallExitButton = Size(
        Gdx.graphics.width * (300f / testedScreen.width),
        Gdx.graphics.height * (100f / testedScreen.height)
    )
    private val heightOfScreenForColors = Gdx.graphics.height * 0.7f
    const val distanceBetweenButtons = 20f
    fun getColorButtonSize(numberOfColors: Int): Size {
        val increaseDistance = 1.5f
        return Size(
            Gdx.graphics.width / 2f - increaseDistance * distanceBetweenButtons,
            heightOfScreenForColors / (numberOfColors / 2) - increaseDistance * distanceBetweenButtons
        )
    }
    fun getScaledFontSize(font: Float): Float {
        return (Gdx.graphics.width + Gdx.graphics.height) * font / (testedScreen.width + testedScreen.height)
    }
}
