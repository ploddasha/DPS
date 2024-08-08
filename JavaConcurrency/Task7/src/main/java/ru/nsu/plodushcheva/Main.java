package ru.nsu.plodushcheva;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int threadsCount = scanner.nextInt();

        final int ITERATIONS_COUNT = 1000;

        Calculations calculations = new Calculations();
        double result = calculations.piCalculate(threadsCount, ITERATIONS_COUNT);
        System.out.println("Pi = " + result);
    }
}
