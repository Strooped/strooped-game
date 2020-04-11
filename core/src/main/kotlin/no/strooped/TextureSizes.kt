package no.strooped

import com.badlogic.gdx.Gdx
import no.strooped.util.Size

object TextureSizes {
    val testedScreen = Size(1080f, 2040f)
    val logo = Size(
        2 * Gdx.graphics.width * (543f / testedScreen.width),
        2 * Gdx.graphics.height * (158f / testedScreen.height)
    )
    val joinButton = Size(
        Gdx.graphics.width * (387f / testedScreen.width),
        Gdx.graphics.height * (100f / testedScreen.height)
    )
    val inputBox = Size(
        Gdx.graphics.width * 0.7f,
        Gdx.graphics.height * 0.05f
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
        Gdx.graphics.width * (130f / testedScreen.width),
        Gdx.graphics.height * (130f / testedScreen.height)
    )
    val exitGameButton = Size(
        Gdx.graphics.width * (376f / testedScreen.width),
        Gdx.graphics.height * (100f / testedScreen.height)
    )

    fun adjustedFontSize(font: Float): Float {
        return (Gdx.graphics.width + Gdx.graphics.height) * font / (testedScreen.width + testedScreen.height)
    }
}
