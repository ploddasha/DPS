package ru.nsu.plodushcheva;

public class PrintStrRunnable implements Runnable{
    private final String str;

    public PrintStrRunnable(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(str);
        }
        System.out.print("Finished");

    }
}
