package no.strooped

import com.badlogic.gdx.Gdx
@Suppress("MagicNumber")
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

        // loadCircle on LobbyScreen
        fun loadWidth(): Float = Gdx.graphics.width * (130f / 1080f) // 105
        fun loadHeight(): Float = Gdx.graphics.height * (130f / 2040f)

        // loadCircle on LobbyScreen
        fun exitGameButtonWidth(): Float = Gdx.graphics.width * (376f / 1080f)
        fun exitGameButtonHeight(): Float = Gdx.graphics.height * (100f / 2040f)

        fun adjustedFontSize(font: Float): Float {
            return (Gdx.graphics.width + Gdx.graphics.height) / (3120f / font)
        }
}
