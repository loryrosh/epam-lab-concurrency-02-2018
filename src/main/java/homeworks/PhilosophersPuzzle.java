package homeworks;

import java.util.concurrent.TimeUnit;

public class PhilosophersPuzzle {

    private static final Object[] chopsticks =
            new Object[]{new Object(), new Object(), new Object(), new Object(), new Object()};

    public static void main(String[] args) {
        Philosopher phil1 = new Philosopher(1, chopsticks[0], chopsticks[1]);
        Philosopher phil2 = new Philosopher(2, chopsticks[1], chopsticks[2]);
        Philosopher phil3 = new Philosopher(3, chopsticks[2], chopsticks[3]);
        Philosopher phil4 = new Philosopher(4, chopsticks[3], chopsticks[4]);
        Philosopher phil5 = new Philosopher(5, chopsticks[4], chopsticks[0]);

        phil1.start();
        phil2.start();
        phil3.start();
        phil4.start();
        phil5.start();
    }

    private static class Philosopher extends Thread {

        private final int id;

        private final Object leftChopstick;
        private final Object rightChopstick;

        Philosopher(int id, Object leftChopstick, Object rightChopstick) {
            this.leftChopstick = leftChopstick;
            this.rightChopstick = rightChopstick;
            this.id = id;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (leftChopstick) {
                    System.out.println(id + " now has left chopstick!");

                    try {
                        System.out.println(id + " is thinking...");
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (rightChopstick) {
                        System.out.println(id + " now has right chopstick!");

                        try {
                            System.out.println(id + " has started eating...");
                            TimeUnit.SECONDS.sleep(2);
                            System.out.println(id + " has finished eating.");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println(id + " has released both chopsticks.");

                try {
                    System.out.println(id + " is thinking...");
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
