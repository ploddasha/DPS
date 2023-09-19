package ru.nsu.plodushcheva;

public class StringPrintRunnable implements Runnable{
    private final String[] strs;

    public StringPrintRunnable(String[] strs) {
        this.strs = strs;
    }

    @Override
    public void run() {
        for (String str : strs) {
            System.out.println(str);
        }
    }

}
