package ru.nsu.plodushcheva;

public class Main {
    public static void main(String[] args) {
        String str = "cool string";
        for (int i = 0; i < 4; i++) {
            new Thread(new StringPrintRunnable(str + " " + i)).start();
        }
    }
}
