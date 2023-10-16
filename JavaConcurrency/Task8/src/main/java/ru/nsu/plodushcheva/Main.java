package ru.nsu.plodushcheva;

import sun.misc.Signal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int threadsCount = scanner.nextInt();
        int seconds = scanner.nextInt();

        Calculations calculations = new Calculations();

        new Thread(() -> {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Signal.raise(new Signal("INT"));
        }).start();

        calculations.piCalculate(threadsCount);
    }
}
