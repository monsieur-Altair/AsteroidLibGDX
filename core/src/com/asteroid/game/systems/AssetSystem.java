package com.asteroid.game.systems;

import com.asteroid.game.GameSettings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

public class AssetSystem implements Disposable {

    private Texture[] playerTextures;
    private Texture[] obstacleTextures;
    private Texture[] backTextures;

    public AssetSystem(){
    }

    public void loadAll(){
        playerTextures = loadTextures(GameSettings.PLAYER_ASSET_FOLDER);
        obstacleTextures = loadTextures(GameSettings.OBSTACLES_ASSET_FOLDER);
        backTextures = loadTextures(GameSettings.BACKS_ASSET_FOLDER);
    }

    public Texture[] getPlayerTextures() {
        return playerTextures;
    }

    public Texture[] getBackTextures() {
        return backTextures;
    }

    private Texture[] loadTextures(final String folderName) {
        final String folderPath = GameSettings.TEXTURES_ASSET_FOLDER + folderName;
        Texture[] array = null;
        FileHandle[] fileHandles = getFileHandles(folderPath);

        if (fileHandles == null)
            return array;

        array = new Texture[fileHandles.length];
        for (int i = 0; i < array.length; i++){
            array[i] = getTexture(fileHandles[i]);
        }

        return array;
    }

    private Texture getTexture(FileHandle fileHandle) {
        //AssetDescriptor<Texture> assetDescriptor = new AssetDescriptor<>(fileHandles[i], Texture.class);
        //assetManager.load(assetDescriptor);
        //return assetManager.get(assetDescriptor);
        return new Texture(fileHandle);
    }

    private FileHandle[] getFileHandles(String folderPath) {
        FileHandle fileHandle = Gdx.files.internal(folderPath);
        if(!fileHandle.exists() || !fileHandle.isDirectory())
            return null;

        return fileHandle.list();
    }


    @Override
    public void dispose() {

        for (Texture playerTexture : playerTextures) {
            playerTexture.dispose();
        }

        for (Texture obstacleTexture : obstacleTextures) {
            obstacleTexture.dispose();
        }
    }
}

