package lesson_16_02_2018;

public class Race {

    public static void main(String[] args) {

    }

    private class Car implements Runnable {
        public float speed = 0;

        public Car(float speed) {
            this.speed = speed;
        }

        @Override
        public void run() {

        }
    }
}


