package issou.logic.random;

import java.util.Random;

public class  RandomFactory {
    private static Random r = new Random();

    public static boolean aBoolean(){
        return r.nextBoolean();
    }
}
