package homeworks.exhibition;


import java.util.concurrent.TimeUnit;

public class Storage {

    private String value = "DEFAULT";
    private final Object monitor = new Object();

    String read() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return value;
    }

    void write(String value) throws InterruptedException {
        synchronized (monitor) {
            this.value = value;
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
