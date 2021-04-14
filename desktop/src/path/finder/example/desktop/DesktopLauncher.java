package path.finder.example.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import path.finder.example.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MyGdxGame(), config) {
			{
				config.title = "Tile Path Finder";
				config.width = 32 * 10;
				config.height = 32 * 10;
			}
		};
	}
}
