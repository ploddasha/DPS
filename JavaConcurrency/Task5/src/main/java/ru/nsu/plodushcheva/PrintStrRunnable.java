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
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                System.out.println("Been interrupted in wait");
                break;
            }
        }
        System.out.println("Finished");

    }
}
