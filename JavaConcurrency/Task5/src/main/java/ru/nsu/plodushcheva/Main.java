package ru.nsu.plodushcheva;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new PrintStrRunnable("New cool string"));
        thread.start();

        try {
            Thread.sleep(2000);
            thread.interrupt();
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
