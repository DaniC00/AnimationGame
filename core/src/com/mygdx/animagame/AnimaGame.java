package com.mygdx.animagame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class AnimaGame extends Game {

	SpriteBatch batch;
	public BitmapFont font;

	public static final int WIDTH = 1000, HEIGHT = 800;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); // use libGDX's default Arial font

		this.setScreen(new AnimaGameMenuScreen(this));
	}

}
