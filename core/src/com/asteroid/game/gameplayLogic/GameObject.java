package com.asteroid.game.gameplayLogic;

public class GameObject extends UpdateObject {

    public final Transform transform;

    public GameObject() {
        this.transform = new Transform();
    }
}
