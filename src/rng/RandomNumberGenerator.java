package rng;

import java.util.Random;

public class RandomNumberGenerator {
    public static Random random = new Random();

    public static int generateNumber(int min, int max){
        return random.nextInt((max - min) + 1) + min;
    }
}
