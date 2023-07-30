package com.asteroid.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Player extends GameObject implements UIRenderObject {

    private final Image mainImage;
    private final float ROTATION_OFFSET = (float) (-Math.PI / 2f);

    public Player(final Texture mainTexture) {
        super();

        this.mainImage = getMainImage(mainTexture);
        mainImage.setPosition(0,0);
    }

    private Image getMainImage(Texture texture) {
        Image image = new Image(texture);
        image.setWidth(texture.getWidth());
        image.setHeight(texture.getWidth());
        return image;
    }

    @Override
    public void update(final float delta) {
        super.update(delta);
        Vector3 translation = transform.translation;
        mainImage.setPosition(translation.x, translation.y);
        //Extensions.log("end pos = " + mainImage.getX() + " " + mainImage.getY());
        float angleInRad = transform.rotation.getAngleAroundRad(GameSettings.forward) + ROTATION_OFFSET;
        float angleInDegrees = (float) Math.toDegrees(angleInRad);
        mainImage.setRotation(angleInDegrees);
        //Extensions.log("end rot = "+ angleInDegrees + " " + mainImage.getRotation());
    }

    @Override
    public void render(final SpriteBatch batch) {
        mainImage.draw(batch, 1f);
    }

    //@Override
    //public Rectangle getRectangle() {
    //    return rectangle;
    //}

    //@Override
    //public Texture getTexture() {
    //    return mainTexture;
    //}
}
