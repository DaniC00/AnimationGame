package com.mygdx.animagame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.animagame.AnimaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "AnimaGame";
		config.width = 590;
		config.height = 700;
		new LwjglApplication(new AnimaGame(), config);
	}
}
