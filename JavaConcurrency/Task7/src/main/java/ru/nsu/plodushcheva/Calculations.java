package ru.nsu.plodushcheva;


public class Calculations {

    public static class ThreadForPi extends Thread {
        private final int iterationsCount;
        private final int countOFThreads;
        private final int num;
        private double result = 0.0;


        public ThreadForPi(int num, int countOFThreads, int iterationsCount) {
            this.num = num;
            this.iterationsCount = iterationsCount;
            this.countOFThreads = countOFThreads;
        }

        public double getResult() {
            return result;
        }

        @Override
        public void run() {
                Pi pi = new Pi();
                result += pi.calculatePiLeibniz(num, countOFThreads, iterationsCount);
        }
    }


    public double piCalculate(int countOfThreads, int iterationsCount) {
        double result = 0;

        ThreadForPi[] threads = new ThreadForPi[countOfThreads];
        for (int i = 0; i < countOfThreads; i++) {
            threads[i] = new ThreadForPi(i, countOfThreads, iterationsCount);
            threads[i].start();
        }

        for (int i = 0; i < countOfThreads; i++) {
            try {
                threads[i].join();
                result += threads[i].getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }


}
