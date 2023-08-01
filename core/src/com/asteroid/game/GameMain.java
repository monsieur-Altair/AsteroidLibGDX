package com.asteroid.game;

import com.asteroid.game.gameplayLogic.BoundChecker;
import com.asteroid.game.gameplayLogic.GameObject;
import com.asteroid.game.gameplayLogic.Level;
import com.asteroid.game.gameplayLogic.UpdateObject;
import com.asteroid.game.systems.AssetSystem;
import com.asteroid.game.systems.CameraSystem;
import com.asteroid.game.systems.ControlSystem;
import com.asteroid.game.systems.InputSystem;
import com.asteroid.game.systems.ObjectFactory;
import com.asteroid.game.systems.UIRenderSystem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

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
	private final BoundChecker boundChecker;

	private Level level;

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

		boundChecker = new BoundChecker(GameSettings.TARGET_WINDOW_WIDTH, GameSettings.TARGET_WINDOW_HEIGHT);
		updatedObjects.add(boundChecker);
	}

	@Override
	public void create() {
		inputSystem.onCreate();
		cameraSystem.onCreate();
		uiRenderSystem.onCreate();
		assetSystem.loadAll();

		level = factory.CreateLevel();

		GameObject player = factory.CreatePlayer();
		controlSystem.setTargetTransform(player.transform);
		controlSystem.switchTo(true);
		boundChecker.addObject(player.transform);
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

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		uiRenderSystem.onResize(width, height);
		boundChecker.updateBounds(width, height);
	}
}

