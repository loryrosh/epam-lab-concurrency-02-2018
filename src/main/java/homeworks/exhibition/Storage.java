package homeworks.exhibition;

import java.util.concurrent.TimeUnit;

public class Storage {

    private String value = "DEFAULT";
    private final Object writingMonitor = new Object();
    private final Object readingMonitor = new Object();

    private volatile boolean readingDenied = false;
    private volatile int counter = 0;

    String read() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " attempts to read.");
        if (readingDenied) {
            synchronized (readingMonitor) {
                System.out.println(Thread.currentThread().getName() + " is waiting to start reading.");
                readingMonitor.wait();
            }
        }

        synchronized (writingMonitor) {
            counter++;
            TimeUnit.SECONDS.sleep(1);
            counter--;

            synchronized (writingMonitor) {
                System.out.println(Thread.currentThread().getName() + " has finished reading.");
                writingMonitor.notify();
            }
        }
        return value;
    }

    void write(String value) throws InterruptedException {
        System.out.println("Writer attempts to write.");
        readingDenied = true;

        // showAllReaders()
        while (counter > 0) {
            synchronized (writingMonitor) {
                System.out.println("Write is waiting to start writing.");
                System.out.println(counter + " readers are still active");
                writingMonitor.wait();
            }
        }

        synchronized (readingMonitor) {
            this.value = value;
            TimeUnit.SECONDS.sleep(1);

            readingDenied = false;
            readingMonitor.notifyAll();
            System.out.println("Writer has finished writing.");
        }
    }

    private void showAllReaders() throws InterruptedException {
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
            }
        }
    }
}
