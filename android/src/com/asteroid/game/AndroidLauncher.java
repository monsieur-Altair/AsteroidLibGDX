package com.asteroid.game;

import android.os.Bundle;

import com.asteroid.game.systems.DesktopInputSystem;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initialize(new GameMain(new DesktopInputSystem()), new AndroidApplicationConfiguration());
	}
}
