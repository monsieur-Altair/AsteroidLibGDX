package com.asteroid.game;

import java.util.List;
import java.util.Random;

public class Extensions {

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    public static <T> T random(List<T> collection, Random random){
        int index = getRandomFromRange(random, 0, collection.size());
        return collection.get(index);
    }

    public static <T> T random(T[] collection, Random random){
        int index = getRandomFromRange(random, 0, collection.length);
        return collection[index];
    }

    public static int getRandomFromRange(Random random, int min, int max){
        return random.nextInt(max - min) + min;
    }

    public static void log(String log){
        if(GameSettings.CanLog)
            System.out.println(log);
    }

}
