package homeworks.exhibition;

import java.util.concurrent.TimeUnit;

public class Storage {

    private String value = "DEFAULT";
    private final Object monitor = new Object();
    private final Object readersMonitor = new Object();

    String read() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return value;
    }

    void write(String value) throws InterruptedException {
        waitForReaders();

        synchronized (monitor) {
            this.value = value;
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Writer: I've written: " + value);

            synchronized (readersMonitor) {
                readersMonitor.notifyAll();
            }
        }
    }

    private void waitForReaders() throws InterruptedException {
        Thread writerThread = Thread.currentThread();
        System.out.println("--> " + writerThread.getThreadGroup().getName() + " "
                + writerThread.getName() + " " + writerThread.getPriority());

        ThreadGroup threadGroup = writerThread.getThreadGroup();
        Thread[] activeThreads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(activeThreads);

        for (Thread thread : activeThreads) {
            if (thread.getName().contains("Reader")) {
                System.out.println("--> " + thread.getThreadGroup().getName() + " "
                        + thread.getName() + " " + thread.getPriority());

                synchronized (readersMonitor) {
                    thread.wait();
                }
            }
        }
    }
}
