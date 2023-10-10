package ru.nsu.plodushcheva;


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
            //result += pi.calculatePiLeibniz(num, countOFThreads);
            pi.calculatePiLeibniz(num, countOFThreads);
        }

        public void stopping() {
            result += pi.stop();
        }
    }


    public double piCalculate(int countOfThreads) {
        double result = 0;

        ThreadForPi[] threads = new ThreadForPi[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            threads[i] = new ThreadForPi(i, countOfThreads);
            threads[i].start();
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //System.exit(0);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            double res = 0.0;
            for (int i = 0; i < countOfThreads; i++) {
                threads[i].stopping();
                res += threads[i].getResult();
            }
            System.out.println("Received SIGINT. Terminating...");
            System.out.println("Pi = " + res);
        }));

        /*
        for (int i = 0; i < countOfThreads; i++) {
            threads[i].stopping();
            result += threads[i].getResult();
        } */

        for (int i = 0; i < countOfThreads; i++) {
            //threads[i].join();
            //result += threads[i].getResult();
            threads[i].interrupt();
        }

        return result;
    }


}