package no.strooped

import com.badlogic.gdx.Gdx
import no.strooped.util.Size

object TextureSizes {
    val testedScreen = Size(1080f, 2040f)
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
    val usernameLabel = Size(
        Gdx.graphics.width * (321f / testedScreen.width),
        Gdx.graphics.height * (62f / testedScreen.height)
    )
    val pinLabel = Size(
        Gdx.graphics.width * (115f / testedScreen.width),
        Gdx.graphics.height * (64f / testedScreen.height)
    )
    val loadSpinner = Size(
        Gdx.graphics.width * (500f / testedScreen.width),
        Gdx.graphics.height * (500f / testedScreen.height)
    )
    val exitGameButton = Size(
        Gdx.graphics.width * (650f / testedScreen.width),
        Gdx.graphics.height * (133f / testedScreen.height)
    )
    private const val numberOfColors = 6 // this should be automatically saved somewhere else
    private val heightOfScreenForColors = Gdx.graphics.height * 0.7f
    const val distanceBetweenButtons = 20f // might change it to be scalable
    val colorButton = Size(
        Gdx.graphics.width / 2f - 1.5f * distanceBetweenButtons,
        heightOfScreenForColors / (numberOfColors / 2) - 1.5f * distanceBetweenButtons
    )

    fun getScaledFontSize(font: Float): Float {
        return (Gdx.graphics.width + Gdx.graphics.height) * font / (testedScreen.width + testedScreen.height)
    }
    // add comments that clarify the class
}
