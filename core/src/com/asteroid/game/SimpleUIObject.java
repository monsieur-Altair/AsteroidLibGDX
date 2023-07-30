package com.asteroid.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class SimpleUIObject implements UIRenderObject {

    private final Image mainImage;

    public SimpleUIObject(Texture mainTexture){
        this.mainImage = getMainImage(mainTexture);
        mainImage.setPosition(0,0);
    }

    @Override
    public void render(final SpriteBatch batch) {
        mainImage.draw(batch, 1f);
    }

    public void updatePosition(float x, float y){
        mainImage.setPosition(x,y);
    }

    public void updateRotation(float angleInDegrees){
        mainImage.setRotation(angleInDegrees);
    }

    private Image getMainImage(Texture texture) {
        Image image = new Image(texture);
        image.setWidth(texture.getWidth());
        image.setHeight(texture.getWidth());
        image.setOrigin(image.getAlign());
        return image;
    }
}
