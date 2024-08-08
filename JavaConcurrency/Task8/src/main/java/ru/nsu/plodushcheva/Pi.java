package ru.nsu.plodushcheva;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Pi extends Thread{
    private double pi = 0.0;
    private final int threadId;
    private final int countOfThreads;
    private final AtomicInteger maxIterations;
    private int i;
    private AtomicBoolean flag;

    public Pi (AtomicInteger maxIterations, int threadId, int countOfThreads) {
        this.maxIterations = maxIterations;
        this.threadId = threadId;
        this.countOfThreads = countOfThreads;
    }


    public void run() {
        i = threadId;
        flag = new AtomicBoolean(false);
        while (i < maxIterations.get()) {
            int denominator = 2 * i + 1;
            pi += Math.pow(-1, i) / denominator;
            i += countOfThreads;
        }
        flag.set(true);
    }

    public double getResult() {
        while(!flag.get()) {}
        return pi * 4;
    }

    public int getIteration() {
        return i;
    }

    public void setMaxIterations(int newMaxIterations) {
        maxIterations.set(newMaxIterations);
    }
}