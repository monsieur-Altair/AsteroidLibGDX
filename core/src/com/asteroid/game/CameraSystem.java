package com.asteroid.game;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraSystem {

    private final OrthographicCamera camera;

    public CameraSystem() {
        this.camera = new OrthographicCamera();
    }

    public void onCreate(){
        camera.setToOrtho(false, GameSettings.TARGET_WINDOW_WIDTH, GameSettings.TARGET_WINDOW_HEIGHT);
    }

    public void update(){
        camera.update();
    }

    public Camera getCamera() {
        return camera;
    }
}
