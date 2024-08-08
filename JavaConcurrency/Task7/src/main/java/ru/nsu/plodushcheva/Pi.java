package ru.nsu.plodushcheva;

public class Pi {
    public double calculatePiLeibniz(int threadId, int countOfThreads, int iterations) {
        int iterationsPerThread = iterations / countOfThreads;
        int startIteration = threadId * iterationsPerThread;
        int endIteration = (threadId == countOfThreads - 1) ?
                iterations : (threadId + 1) * iterationsPerThread;

        double pi = 0.0;
        boolean subtract = startIteration % 2 == 1;

        for (int i = startIteration; i < endIteration; i++) {
            int denominator = 2 * i + 1;
            double term = 1.0 / denominator;

            if (subtract) {
                pi -= term;
            } else {
                pi += term;
            }
            subtract = !subtract;
        }
        System.out.println(pi * 4);
        return pi * 4;
    }
}
