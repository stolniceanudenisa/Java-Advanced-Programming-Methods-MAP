package org.example;

import java.util.concurrent.CountDownLatch;

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        try {
            startSignal.await(); // Wait until the start signal is counted down to zero
            // Perform some task
            System.out.println("Thread " + Thread.currentThread().getId() + " is working...");
            doneSignal.countDown(); // Signal that this task is done
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}