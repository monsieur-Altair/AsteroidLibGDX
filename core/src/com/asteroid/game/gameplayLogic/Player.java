package com.asteroid.game.gameplayLogic;

import com.asteroid.game.GameSettings;
import com.asteroid.game.gameplayLogic.GameObject;
import com.asteroid.game.gameplayLogic.SimpleUIObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player extends GameObject {

    private final SimpleUIObject visual;
    private final float ROTATION_OFFSET = (float) (-Math.PI / 2f);

    public Player(final Texture mainTexture) {

        super();
        visual = new SimpleUIObject(mainTexture);
    }

    public SimpleUIObject getVisual() {
        return visual;
    }

    @Override
    public void update(final float delta) {
        super.update(delta);
        float angleInRad = transform.rotation.getAngleAroundRad(GameSettings.forward) + ROTATION_OFFSET;
        float angleInDegrees = (float) Math.toDegrees(angleInRad);
        visual.updateRotation(angleInDegrees);

        Vector3 translation = transform.translation;
        visual.updatePosition(translation.x, translation.y);
        //Extensions.log(translation.x +" "+ translation.y);
    }
}

