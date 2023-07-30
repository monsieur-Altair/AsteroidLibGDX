package com.asteroid.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class BoundChecker extends UpdateObject implements Disposable {
    private final Vector2 xBounds;
    private final Vector2 yBounds;
    private final Array<Transform> checkedObjects;

    public BoundChecker(float width, float height) {
        xBounds = new Vector2(0, width);
        yBounds = new Vector2(0, height);
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
}
