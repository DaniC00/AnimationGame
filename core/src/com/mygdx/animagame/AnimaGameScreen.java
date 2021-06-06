package com.mygdx.animagame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class AnimaGameScreen implements Screen {
	final AnimaGame game;
	public BitmapFont font;

	private static final int FRAME_COLS = 4, FRAME_ROWS = 4;

	Animation<TextureRegion> walkDownAnimation;
	Animation<TextureRegion> walkLeftAnimation;
	Animation<TextureRegion> walkRightAnimation;
	Animation<TextureRegion> walkUpAnimation;

	OrthographicCamera camera;

	Rectangle trainer;
	Texture trainerSprite;

	Texture walkSheet;

	float stateTime;

	public AnimaGameScreen(final AnimaGame game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, AnimaGame.WIDTH, AnimaGame.HEIGHT);

		walkSheet = new Texture(Gdx.files.internal("pokemon_trainer.png"));

		TextureRegion[][] tmp = TextureRegion.split(walkSheet,
				walkSheet.getWidth() / FRAME_COLS,
				walkSheet.getHeight() / FRAME_ROWS);

		// create the camera and the SpriteBatch
		TextureRegion[] walkDownFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] walkRightFrames = new TextureRegion[FRAME_COLS];
		TextureRegion[] walkUpFrames = new TextureRegion[FRAME_COLS];


		int index = 0;

		for (int i = 0; i < FRAME_ROWS; i++) {
			index = 0;
			for (int j = 0; j < FRAME_COLS; j++) {
				if (i == 0)
					walkDownFrames[index++] = tmp[i][j];
				if (i == 1)
					walkLeftFrames[index++] = tmp[i][j];
				if (i == 2)
					walkRightFrames[index++] = tmp[i][j];
				if (i == 3)
					walkUpFrames[index++] = tmp[i][j];
			}
		}

		walkDownAnimation = new Animation<TextureRegion>(0.076f, walkDownFrames);
		walkLeftAnimation = new Animation<TextureRegion>(0.076f, walkLeftFrames);
		walkRightAnimation = new Animation<TextureRegion>(0.076f, walkRightFrames);
		walkUpAnimation = new Animation<TextureRegion>(0.076f, walkUpFrames);


		trainer = new Rectangle();
		trainer.x = 400;
		trainer.y = 400;
		trainer.height = 64;
		trainer.width = 64;

		stateTime = 0f;

	}


	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		stateTime += Gdx.graphics.getDeltaTime();

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		TextureRegion currentDownFrame = walkDownAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentLeftFrame = walkLeftAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentRightFrame = walkRightAnimation.getKeyFrame(stateTime, true);
		TextureRegion currentUpFrame = walkUpAnimation.getKeyFrame(stateTime, true);



		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			game.batch.draw(walkLeftAnimation.getKeyFrame(stateTime, true), trainer.x, trainer.y);
			trainer.x -= 100 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			game.batch.draw(walkRightAnimation.getKeyFrame(stateTime, true), trainer.x, trainer.y);
			trainer.x += 120 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			game.batch.draw(walkUpAnimation.getKeyFrame(stateTime, true), trainer.x, trainer.y);
			trainer.y += 120 * Gdx.graphics.getDeltaTime();
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			game.batch.draw(walkDownAnimation.getKeyFrame(stateTime, true), trainer.x, trainer.y);
			trainer.y -= 100 * Gdx.graphics.getDeltaTime();
		} else {
			game.batch.draw(walkDownAnimation.getKeyFrames()[1], trainer.x, trainer.y, trainer.width, trainer.height);
		}

		game.batch.end();

	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		game.batch.dispose();
	}

}