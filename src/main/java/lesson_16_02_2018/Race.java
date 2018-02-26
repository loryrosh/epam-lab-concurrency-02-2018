package lesson_16_02_2018;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Race {

    private static final int CARS_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch1 = new CountDownLatch(CARS_COUNT);

        for (int i = 0; i < CARS_COUNT; i++) {
            new Thread(new Car(ThreadLocalRandom.current().nextInt(), String.valueOf("Car-" + (i + 1))))
                    .start();
        }

        System.out.println("Ready");
        TimeUnit.SECONDS.sleep(1);
    }

    private static class Car implements Runnable {
        public float speed;
        public String name;

        public Car(float speed, String name) {
            this.speed = speed;
            this.name = name;
        }

        @Override
        public void run() {

        }
    }
}


