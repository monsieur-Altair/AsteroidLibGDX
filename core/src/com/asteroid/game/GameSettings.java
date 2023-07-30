package com.asteroid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

public class GameSettings {
    public static final String PLAYER_ASSET_FOLDER = "player";
    public static final String OBSTACLES_ASSET_FOLDER = "obstacles";
    public static final String TEXTURES_ASSET_FOLDER = "textures/";

    public static final int TARGET_WINDOW_WIDTH = 1080;
    public static final int TARGET_WINDOW_HEIGHT = 1080;
    public static final int FPS = 60;

    public static final int NEGATIVE_X_AXIS = Input.Keys.LEFT;
    public static final int POSITIVE_X_AXIS = Input.Keys.RIGHT;
    public static final int POSITIVE_Y_AXIS = Input.Keys.UP;
    public static final int NEGATIVE_Y_AXIS = Input.Keys.DOWN;

    public static float MotionSpeed = 50f;
    public static final Vector3 forward = new Vector3(0,0,1);

    public static boolean CanLog = true;
}
