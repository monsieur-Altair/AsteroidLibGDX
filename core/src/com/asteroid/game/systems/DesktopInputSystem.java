package com.asteroid.game.systems;

import com.asteroid.game.Extensions;
import com.asteroid.game.GameSettings;
import com.asteroid.game.events.Event;
import com.asteroid.game.systems.InputSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class DesktopInputSystem implements Disposable, InputSystem {

    private Input input;
    private final Event updated;
    private final Event exitCalled;
    private final Vector3 inputAxis;
    private final Vector3 cursorPosition;

    public DesktopInputSystem(){
        cursorPosition = new Vector3(Vector3.Zero);
        inputAxis = new Vector3(Vector3.Zero);
        updated = new Event();
        exitCalled = new Event();
    }

    public void onCreate(){
        input = Gdx.input;
    }

    @Override
    public void dispose() {
        updated.dispose();
    }

    @Override
    public Vector3 getCursorPosition() {
        return cursorPosition;
    }

    @Override
    public Vector3 getAxisInput() {
        return inputAxis;
    }

    @Override
    public Event getUpdateEvent() {
        return updated;
    }

    @Override
    public Event getExitEvent() {
        return exitCalled;
    }

    public void update(final float deltaTime, final Camera camera){

        if(input.isKeyPressed(Input.Keys.ESCAPE))
        {
            exitCalled.invoke();
            return;
        }

        cursorPosition.set(input.getX(), input.getY(), 0);
        camera.unproject(cursorPosition);

        float x = getAxisValue(deltaTime, GameSettings.NEGATIVE_X_AXIS, GameSettings.POSITIVE_X_AXIS, inputAxis.x);
        float y = getAxisValue(deltaTime, GameSettings.NEGATIVE_Y_AXIS, GameSettings.POSITIVE_Y_AXIS, inputAxis.y);
        inputAxis.set(x, y, 0f);
        //Extensions.log("input = " + inputAxis.x + " " + inputAxis.y);
        updated.invoke();
    }

    private float getAxisValue(final float deltaTime, final int negativeKey, final int positiveKey, final float startValue) {
        float deltaAxis = startValue;
        float axisSpeed = 1f;
        float backSpeed = 0.4f;

        if(input.isKeyPressed(negativeKey)) {
            deltaAxis -= axisSpeed * deltaTime;
        }
        if(input.isKeyPressed(positiveKey)) {
            deltaAxis += axisSpeed * deltaTime;
        }

        float sign = Math.signum(deltaAxis);
        deltaAxis = Extensions.clamp(Math.abs(deltaAxis) - backSpeed * deltaTime, 0f, 1f);
        return sign * deltaAxis;
    }
}

