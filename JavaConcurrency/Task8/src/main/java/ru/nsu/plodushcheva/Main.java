package ru.nsu.plodushcheva;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int threadsCount = scanner.nextInt();

        Calculations calculations = new Calculations();
        double result = calculations.piCalculate(threadsCount);
        System.out.println("Pi = " + result);
    }
}
