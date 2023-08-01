package com.asteroid.game.gameplayLogic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;

public class SimpleUIObject implements UIRenderObject {

    private final Image mainImage;
    private final int align;
    private final boolean resizable;

    public SimpleUIObject(Texture mainTexture){
        this(mainTexture, mainTexture.getWidth(), mainTexture.getHeight(), Align.center, 0, 0, false);
    }

    public SimpleUIObject(Texture mainTexture, float width, float height, int align, float x, float y, boolean resizable){
        this.resizable = resizable;
        this.mainImage = getMainImage(mainTexture, width, height);
        this.align = align;
        updatePosition(x,y);
    }

    @Override
    public void render(final SpriteBatch batch) {
        mainImage.draw(batch, 1f);
    }

    @Override
    public void resize(int width, int height) {
//        if(!resizable)
//            return;
//
//        float newAspect = (float) width / height;
//        float imageAspect = mainImage.getWidth() / mainImage.getHeight();
//        float targetAspect = (float) GameSettings.TARGET_WINDOW_WIDTH/GameSettings.TARGET_WINDOW_HEIGHT;
//
//        if(newAspect > targetAspect){
//            float newWidth = GameSettings.TARGET_WINDOW_HEIGHT * newAspect;
//            mainImage.setWidth(newWidth);
//            mainImage.setHeight(newWidth /imageAspect);
//        }
//        else{
//            float newHeight = GameSettings.TARGET_WINDOW_WIDTH / newAspect;
//            mainImage.setHeight(newHeight);
//            mainImage.setWidth(newHeight * imageAspect);
//        }
//
//        updatePosition(mainImage.getWidth()/2, mainImage.getHeight()/2);
//
//        Extensions.log(mainImage.getImageWidth() + " " + mainImage.getImageHeight());
    }

    public void updatePosition(float x, float y){
        //mainImage.setPosition(x - mainImage.getImageWidth()/2f,y - mainImage.getHeight()/2f);
        mainImage.setX(x, align);
        mainImage.setY(y, align);
    }

    public void updateRotation(float angleInDegrees){
        mainImage.setRotation(angleInDegrees);
    }

    private Image getMainImage(Texture texture, float width, float height) {
        Image image = new Image(texture);
        image.setWidth(width);
        image.setHeight(height);
        image.setOrigin(image.getAlign());
        return image;
    }
}
