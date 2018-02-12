package homeworks.exhibition;

import java.util.concurrent.TimeUnit;

public class Writer extends Thread {

    private final Storage storage;

    public Writer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                storage.write(String.valueOf(i));
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
