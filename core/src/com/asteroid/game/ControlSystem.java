package com.asteroid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class ControlSystem implements Disposable {

    private final InputSystem inputSystem;
    private Transform targetTransform;

    private final EventListener onInputUpdated = this::onInputUpdated;
    private final EventListener onExitCalled = this::onExitCalled;

    private final float motionSpeed;

    private final Vector3 delta;

    private boolean isEnabled = false;

    public ControlSystem(InputSystem inputSystem){
        this.inputSystem = inputSystem;

        motionSpeed = GameSettings.MotionSpeed;
        delta = new Vector3(Vector3.Zero);

        inputSystem.getUpdateEvent().subscribe(onInputUpdated);
        inputSystem.getExitEvent().subscribe(onExitCalled);
    }

    public void setTargetTransform(Transform targetTransform){
        this.targetTransform = targetTransform;
    }

    public void switchTo(boolean isEnabled){
        this.isEnabled = isEnabled;
    }

    private void onInputUpdated(){
        if(!isEnabled || targetTransform == null){
            return;
        }
        Vector3 axisInput = inputSystem.getAxisInput();
        //Extensions.log("res = " + axisInput.x + " " + axisInput.y);
        targetTransform.translation.add(axisInput.x * motionSpeed, axisInput.y * motionSpeed, axisInput.z * motionSpeed);
        //Extensions.log("target = " + targetTransform.translation.x + " " + targetTransform.translation.y);
        Vector3 lookAtPos = inputSystem.getCursorPosition();
        Vector3 currentPos = targetTransform.translation;
        delta.set(lookAtPos.x - currentPos.x, lookAtPos.y - currentPos.y, lookAtPos.z - currentPos.z);
        float angle = (float) Math.atan2(delta.y, delta.x);
        //Extensions.log("angle from contr " + angle);
        targetTransform.rotation.setFromAxisRad(GameSettings.forward, angle);
    }

    private void onExitCalled() {
        Gdx.app.exit();
    }

    @Override
    public void dispose() {
        inputSystem.getUpdateEvent().unSubscribe(onInputUpdated);
        inputSystem.getExitEvent().unSubscribe(onExitCalled);
    }
}

