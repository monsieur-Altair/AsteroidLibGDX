package com.asteroid.game;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Transform {
    public final Vector3 translation = new Vector3();
    public final Quaternion rotation = new Quaternion();
    public final Vector3 scale = new Vector3(1, 1, 1);

    public Transform() {
        translation.set(0, 0, 0);
        rotation.idt();
        scale.set(1, 1, 1);
    }

//    public void set(final Vector3 t, final Quaternion r, final Vector3 s) {
//        translation.set(t);
//        rotation.set(r);
//        scale.set(s);
//    }
//
//    public void setTranslation(final Vector3 t){
//        translation.set(t);
//    }

}
