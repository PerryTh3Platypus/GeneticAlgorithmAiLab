package rng;

import java.util.Random;

public class RandomNumberGenerator {
    public static Random random = new Random();

    public static int generateNumber(int min, int max){
        int bound = (max - min) + 1;
        if (bound < 0)
            bound = 0;
        return random.nextInt(bound) + min;
    }
}
