package homeworks.exhibition;

import java.util.stream.IntStream;

public class Launcher {

    public static void main(String[] args) {
        Storage storage = new Storage();

        Writer writer = new Writer(storage);
        writer.setName("Writer");
        writer.start();

        IntStream.range(0, 4)
                 .mapToObj(i -> new Reader(storage, String.valueOf("Reader-" + i)))
                 .forEach(Thread::start);
    }
}
