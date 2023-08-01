package com.asteroid.game.systems;

import com.asteroid.game.Extensions;
import com.asteroid.game.GameMain;
import com.asteroid.game.gameplayLogic.GameObject;
import com.asteroid.game.GameSettings;
import com.asteroid.game.gameplayLogic.Level;
import com.asteroid.game.gameplayLogic.Player;
import com.asteroid.game.gameplayLogic.SimpleUIObject;
import com.asteroid.game.gameplayLogic.UIRenderObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;

import java.util.Random;

public class ObjectFactory {

    private final AssetSystem assetSystem;
    private final Random random;
    private final UIRenderSystem renderSystem;
    private final GameMain gameMain;

    public ObjectFactory(AssetSystem assetSystem, Random random, UIRenderSystem renderSystem, GameMain gameMain){
        this.assetSystem = assetSystem;
        this.random = random;
        this.renderSystem = renderSystem;
        this.gameMain = gameMain;
    }

    public GameObject CreatePlayer(){
        Texture[] playerTextures = assetSystem.getPlayerTextures();
        Texture randomTexture = Extensions.random(playerTextures, random);
        Player player = new Player(randomTexture);
        renderSystem.addObject(player.getVisual());
        gameMain.getUpdatedObjects().add(player);
        return player;
    }

    public Level CreateLevel(){
        Texture backTexture = Extensions.random(assetSystem.getBackTextures(), random);
        int targetWindowWidth = GameSettings.TARGET_WINDOW_WIDTH;
        int targetWindowHeight = GameSettings.TARGET_WINDOW_HEIGHT;
        int width = targetWindowWidth;
        int height = targetWindowHeight;
        UIRenderObject back = new SimpleUIObject(backTexture, width, height, Align.center, targetWindowWidth/2f,targetWindowHeight/2f, true);
        renderSystem.addObject(back);
        return new Level(back);
    }
}

