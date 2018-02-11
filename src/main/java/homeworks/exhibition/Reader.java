package homeworks.exhibition;

import java.util.concurrent.TimeUnit;

public class Reader extends Thread {

    private final Storage storage;

    public Reader(Storage storage, String name) {
        super(name);
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("The value I've read is: " + storage.read());
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
