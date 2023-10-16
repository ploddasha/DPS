package ru.nsu.plodushcheva;

import sun.misc.Signal;
import sun.misc.SignalHandler;
import java.util.concurrent.Semaphore;

public class Calculations {

    public static class ThreadForPi extends Thread {
        private final int countOFThreads;
        private final int num;
        private double result = 0.0;
        private Pi pi;


        public ThreadForPi(int num, int countOFThreads) {
            this.num = num;
            this.countOFThreads = countOFThreads;
        }

        public double getResult() {
            return result;
        }

        @Override
        public void run() {
            pi = new Pi();
            pi.calculatePiLeibniz(num, countOFThreads);
        }

        public void stopping() {
            result += pi.stop();
        }
    }


    public void piCalculate(int countOfThreads) {

        ThreadForPi[] threads = new ThreadForPi[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            threads[i] = new ThreadForPi(i, countOfThreads);
            threads[i].start();
        }

        Semaphore semaphore = new Semaphore(0);

        SignalHandler signalHandler = signal -> {
            double res = 0.0;
            for (int i = 0; i < countOfThreads; i++) {
                threads[i].stopping();
                res += threads[i].getResult();
            }
            System.out.println("Received SIGINT. Terminating...");
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

