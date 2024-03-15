package org.example;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int workerCount = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(workerCount);

        // Create and start threads
        for (int i = 0; i < workerCount; ++i) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        // Perform some setup tasks

        // Start all threads at once by counting down the latch
        startSignal.countDown();

        // Wait until all threads finish their tasks
        doneSignal.await();

        // Perform post-processing tasks
        System.out.println("All threads have completed their work.");
    }
}