package com.asteroid.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public interface UIRenderObject{
//    Rectangle getRectangle();
//    Texture getTexture();

    void render(final SpriteBatch batch);
}