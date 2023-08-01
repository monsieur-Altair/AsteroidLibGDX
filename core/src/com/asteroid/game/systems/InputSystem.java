package com.asteroid.game.systems;

import com.asteroid.game.events.Event;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public interface InputSystem extends Disposable {
    Vector3 getCursorPosition();
    Vector3 getAxisInput();
    Event getUpdateEvent();
    Event getExitEvent();
    void update(final float deltaTime, final Camera camera);
    void onCreate();
}

