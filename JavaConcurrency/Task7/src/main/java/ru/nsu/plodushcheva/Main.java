package ru.nsu.plodushcheva;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int threadsCount = scanner.nextInt();

        int iterationsCount = 1000;
        if (args.length > 1) {
            iterationsCount = Integer.parseInt(args[0]);
        }

        Calculations calculations = new Calculations();
        double result = calculations.piCalculate(threadsCount, iterationsCount);
        System.out.println("Pi = " + result);
    }
}
