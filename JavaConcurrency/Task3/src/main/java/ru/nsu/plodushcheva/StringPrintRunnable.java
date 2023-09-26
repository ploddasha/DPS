package ru.nsu.plodushcheva;

public class StringPrintRunnable implements Runnable{
    private final String str;

    public StringPrintRunnable(String str) {
        this.str = new String(str);
    }

    @Override
    public void run() {
        System.out.println(str);
    }

}
