package com.mygdx.animagame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class AnimaGameMenuScreen implements Screen {

    final AnimaGame game;
    OrthographicCamera camera;

    public AnimaGameMenuScreen(AnimaGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, AnimaGame.WIDTH, AnimaGame.HEIGHT);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Bienvenido a ANIMAGAME!!! ", 200, 350);
        game.font.draw(game.batch, "Haz Click para empezar!", 200, 300);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new AnimaGameScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
