package utilities;

import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * User: sigurd
 * Date: 11/11/13
 * Time: 16:47
 */
public class Util {

    public static double getRandomDouble(double min, double max) {
        Random random = new Random();
        return min + (max - min) * random.nextDouble();
    }

    public static int getRandomInteger(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }

    public static void sleepThread(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
