package issou.logic.utils;

import java.util.Random;

public class RandomFactory {
    private static final Random r = new Random();

    public static boolean aBoolean(){
        return r.nextBoolean();
    }
}
