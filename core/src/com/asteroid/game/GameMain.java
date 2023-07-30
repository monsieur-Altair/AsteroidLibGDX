package com.asteroid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GameMain extends ApplicationAdapter {

	private final InputSystem inputSystem;
	private final ObjectFactory factory;
	private final CameraSystem cameraSystem;
	private final UIRenderSystem uiRenderSystem;
	private final AssetSystem assetSystem;
	private final Array<Disposable> disposables;
	private final Array<UpdateObject> updatedObjects;
	private final ControlSystem controlSystem;
	private final Random random;

	public GameMain(InputSystem inputSystem){

		random = new Random();
		disposables = new Array<>();
		updatedObjects = new Array<>();

		this.inputSystem = inputSystem;
		disposables.add(this.inputSystem);

		controlSystem = new ControlSystem(inputSystem);
		disposables.add(controlSystem);

		assetSystem = new AssetSystem();
		disposables.add(assetSystem);

		cameraSystem = new CameraSystem();

		uiRenderSystem = new UIRenderSystem(cameraSystem);
		disposables.add(uiRenderSystem);

		factory = new ObjectFactory(assetSystem, random, uiRenderSystem, this);
	}

	@Override
	public void create() {
		inputSystem.onCreate();
		uiRenderSystem.onCreate();
		cameraSystem.onCreate();
		assetSystem.loadAll();

		GameObject player = factory.CreatePlayer();
		controlSystem.setTargetTransform(player.transform);
		controlSystem.switchTo(true);
	}

	@Override
	public void render () {
		final float deltaTime = Gdx.graphics.getDeltaTime();

		cameraSystem.update();
		inputSystem.update(deltaTime, cameraSystem.getCamera());
		updatedObjects.forEach(obj -> obj.update(deltaTime));
		uiRenderSystem.render();
	}
	
	@Override
	public void dispose () {
		controlSystem.switchTo(false);
		disposables.forEach(Disposable::dispose);
	}

	public Array<UpdateObject> getUpdatedObjects() {
		return updatedObjects;
	}

}

