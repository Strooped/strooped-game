package no.strooped

import com.badlogic.gdx.Gdx

object TextureSizes {
        // logo on JoinGameScreen
        fun logoWidth(): Float = 2 * Gdx.graphics.width * (543f / 1080f)
        fun logoHeight(): Float = 2 * Gdx.graphics.height * (158f / 2040f)

        // joinGameButton on JoinGameScreen
        fun joinGameButtonWidth(): Float = Gdx.graphics.width * (387f / 1080f)
        fun joinGameButtonHeight(): Float = Gdx.graphics.height * (100f / 2040f)

        // inputBox on JoinGameScreen
        fun inputBoxWidth(): Float = Gdx.graphics.width * 0.7f
        fun inputBoxHeight(): Float = Gdx.graphics.height * 0.05f

        // usernameLabel on JoinGameScreen
        fun userLabelWidth(): Float = Gdx.graphics.width * (321f / 1080f)
        fun userLabelHeight(): Float = Gdx.graphics.height * (62f / 2040f)

        // pinLabel on JoinGameScreen
        fun pinLabelWidth(): Float = Gdx.graphics.width * (115f / 1080f)
        fun pinLabelHeight(): Float = Gdx.graphics.height * (64f / 2040f)
}
