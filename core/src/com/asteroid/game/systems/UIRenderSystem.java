package com.asteroid.game.systems;

import com.asteroid.game.GameSettings;
import com.asteroid.game.gameplayLogic.UIRenderObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UIRenderSystem implements Disposable {

    private SpriteBatch spriteBatch;
    private final CameraSystem cameraSystem;
    private final Array<UIRenderObject> renderObjects;

    private Viewport viewport;

    public UIRenderSystem(CameraSystem cameraSystem) {
        this.cameraSystem = cameraSystem;
        renderObjects = new Array<>();
    }

    public void onCreate(){
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(GameSettings.TARGET_WINDOW_WIDTH, GameSettings.TARGET_WINDOW_HEIGHT, cameraSystem.getCamera());
    }

    public void addObject(UIRenderObject newObj){
        renderObjects.add(newObj);
    }

    public void removeObject(UIRenderObject removedObj){
        renderObjects.removeValue(removedObj, true);
    }

    public void onResize(int width, int height){
        viewport.update(width, height);

        Vector2 unproject = viewport.project(Vector2.Zero);
        //Extensions.log(unproject.x + " "+unproject.y);
        //renderObjects.forEach(renderObject -> renderObject.resize(width, height));
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
