package com.asteroid.game.gameplayLogic;

import com.asteroid.game.gameplayLogic.Transform;
import com.asteroid.game.gameplayLogic.UpdateObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class BoundChecker extends UpdateObject implements Disposable {
    private Vector2 xBounds;
    private int targetWidth;
    private Vector2 yBounds;
    private int targetHeight;
    private final Array<Transform> checkedObjects;

    public BoundChecker(int targetWidth, int targetHeight) {
        xBounds = new Vector2(0, targetWidth);
        yBounds = new Vector2(0, targetHeight);

        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;

        checkedObjects = new Array<>();
    }

    public void addObject(Transform obj){
        checkedObjects.add(obj);
    }

    public void removeObject(Transform obj){
        checkedObjects.removeValue(obj, true);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        checkedObjects.forEach(this::check);
    }

    private void check(Transform transform){
        Vector3 translation = transform.translation;
        float x = check(translation.x, xBounds);
        float y = check(translation.y, yBounds);
        translation.set(x, y, translation.z);
    }

    private float check(float checkedValue, Vector2 bounds) {

        float delta = checkedValue - bounds.x;
        if(delta < 0)
            return bounds.y + delta;

        float delta1 = checkedValue - bounds.y;
        if (delta1 > 0)
            return bounds.x + delta1;

        return checkedValue;
    }

    @Override
    public void dispose() {
        checkedObjects.clear();
    }

    public void updateBounds(int width, int height) {
        float aspect = (float) width / height;
        float targetAspect = (float) targetWidth / targetHeight;
        if(aspect > targetAspect)
        {
            float offset = (aspect - targetAspect) * targetWidth /2f;
            xBounds = new Vector2(-offset, targetHeight * aspect - offset );
        }
        else
        {
            float offset = (targetAspect - aspect) * targetHeight /2f;
            yBounds = new Vector2(-offset, targetWidth / aspect - offset);
        }

        //Extensions.log(" w = " +width + " h = "+height);
        //Extensions.log(" w = " +xBounds + " h = "+yBounds);
    }
}
