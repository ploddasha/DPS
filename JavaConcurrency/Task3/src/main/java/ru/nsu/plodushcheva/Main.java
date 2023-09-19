package ru.nsu.plodushcheva;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            new Thread(new StringPrintRunnable(new String[] {Integer.toString(i), "e"})).start();
        }

    }
}
