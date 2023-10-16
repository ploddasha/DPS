package ru.nsu.plodushcheva;

public class Pi {
    private boolean workFlag = true;
    private volatile double pi = 0.0;
    private final Object lock = new Object();



    public void calculatePiLeibniz(int threadId, int countOfThreads) {

        int i = threadId;
        boolean subtract = threadId % 2 == 1;


        while (workFlag) {
            int denominator = 2 * i + 1;
            double term = 1.0 / denominator;

            synchronized (lock) {
                if (subtract) {
                    pi -= term;
                } else {
                    pi += term;
                }
            }
            subtract = !subtract;
            i += countOfThreads;
        }
    }

    public double stop() {
        workFlag = false;
        double result;
        synchronized (lock) {
            result = pi * 4;
        }
        System.out.println("step = " + result);
        return result;
    }
}