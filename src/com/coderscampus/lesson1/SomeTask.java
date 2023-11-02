package com.coderscampus.lesson1;

import java.util.Random;

public class SomeTask implements Runnable {

    static final double INT_MULTIPLIER = 1.5;
        @Override
        public void run() {

            for (int i = 0; i < 1000000; i++) {
                Integer randomInt_1 = new Random().nextInt();
                Integer randomInt_2 = new Random().nextInt();
                Double randomValue = (randomInt_1 * randomInt_2) * INT_MULTIPLIER;
//                System.out.println("Random Value: " + randomValue);
            }
            System.out.println("Hey look at me, I'm a task! I'm running on thread: " + Thread.currentThread().getName());
        }
}
