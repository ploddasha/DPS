package ru.nsu.plodushcheva;

public class Pi {
    private boolean workFlag = true;
    private volatile double pi = 0.0;

    public void calculatePiLeibniz(int threadId, int countOfThreads) {

        int i = threadId;
        boolean subtract = threadId % 2 == 1;


        while (workFlag) {
            int denominator = 2 * i + 1;
            double term = 1.0 / denominator;

            if (subtract) {
                pi -= term;
            } else {
                pi += term;
            }
            subtract = !subtract;
            i += countOfThreads;
        }
        System.out.println(pi * 4);
    }

    public double stop() {
        workFlag = false;
        System.out.println(pi * 4);
        return pi * 4;
    }
}