package ru.nsu.plodushcheva;

import sun.misc.Signal;
import sun.misc.SignalHandler;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Calculations {

    static AtomicInteger atomicInt = new AtomicInteger(100000000);

    public void piCalculate(int countOfThreads) {

        Pi[] threads = new Pi[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            threads[i] = new Pi(atomicInt, i, countOfThreads);
            threads[i].start();
        }

        Semaphore semaphore = new Semaphore(0);

        SignalHandler signalHandler = signal -> {
            int maxIteration = 0;
            for (Pi thread : threads) {
                int iteration = thread.getIteration();
                if (iteration > maxIteration) {
                    maxIteration = iteration;
                }
            }

            for (Pi thread : threads) {
                thread.setMaxIterations(maxIteration);
            }

            double res = 0.0;
            for (Pi thread : threads) {
                res += thread.getResult();
            }

            System.out.println("Received SIGINT");
            System.out.println("Pi = " + res);
            semaphore.release();
        };

        Signal.handle(new Signal("INT"), signalHandler);

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

