package ru.nsu.plodushcheva;

public class Main {
    public static void main(String[] args) {
        String[] str = new String[] {"cool ", "string ", "i"};
        for (int i = 0; i < 4; i++) {
            str[2] = Integer.toString(i);
            new Thread(new StringPrintRunnable(str)).start();
        }
    }
}
