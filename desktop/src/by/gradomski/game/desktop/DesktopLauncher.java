package by.gradomski.game.desktop;

import by.gradomski.game.ZombieGame;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Gradomski game";
		config.height = 800;
		config.width = 1200;
		new LwjglApplication(new ZombieGame(), config);
	}
}
