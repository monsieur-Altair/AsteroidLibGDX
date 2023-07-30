package com.asteroid.game;

public class GameObject extends UpdateObject {
    public final Transform transform;

    public GameObject() {
        this.transform = new Transform();
    }
}
