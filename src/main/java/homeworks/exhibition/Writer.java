package homeworks.exhibition;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public class Writer extends Thread {

    private final Storage storage ;

    public Writer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            storage.write(String.valueOf(i));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
