package com.asteroid.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;

public class UIRenderSystem implements Disposable {

    private SpriteBatch spriteBatch;
    private final CameraSystem cameraSystem;
    private final Array<UIRenderObject> renderObjects;

    public UIRenderSystem(CameraSystem cameraSystem) {
        this.cameraSystem = cameraSystem;
        renderObjects = new Array<>();
    }

    public void onCreate(){
        this.spriteBatch = new SpriteBatch();
    }

    public void addObject(UIRenderObject newObj){
        renderObjects.add(newObj);
    }

    public void removeObject(UIRenderObject removedObj){
        renderObjects.removeValue(removedObj, true);
    }

    public void render(){
        ScreenUtils.clear(0, 0, 0, 1);

        spriteBatch.setProjectionMatrix(cameraSystem.getCamera().combined);
        spriteBatch.begin();

        for(UIRenderObject uiRenderObject : renderObjects) {
            uiRenderObject.render(spriteBatch);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        renderObjects.clear();
    }
}
