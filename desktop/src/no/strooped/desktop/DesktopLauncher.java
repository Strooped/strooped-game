package no.strooped.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import no.strooped.Strooped;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 480;
        config.height = 700;
        config.title = Strooped.TITLE;
        config.x = 400;
        config.y = 0;
        new LwjglApplication(new Strooped(), config);
	}
}
