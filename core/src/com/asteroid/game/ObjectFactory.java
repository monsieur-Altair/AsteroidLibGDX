package com.asteroid.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

public class ObjectFactory {

    private final AssetSystem assetSystem;
    private final Random random;
    private final UIRenderSystem renderSystem;
    private GameMain gameMain;

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
        renderSystem.addObject(player);
        gameMain.getUpdatedObjects().add(player);
        return player;
    }


}

