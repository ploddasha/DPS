package ru.nsu.plodushcheva;

public class Main {
    public static void main(String []args) throws InterruptedException {
        Thread thread = new ChildThread();
        thread.start();
        thread.join();
        for (int i = 0; i < 10; i++) {
            System.out.println("Line from parent");
        }
    }
}
