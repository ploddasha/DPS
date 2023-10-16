package ru.nsu.plodushcheva;

import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore parentSemaphore = new Semaphore(1);
        Semaphore childSemaphore = new Semaphore(0);

        Thread childThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    parentSemaphore.acquire();
                    System.out.println("Line from child");
                    childSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        childThread.start();

        for (int i = 0; i < 10; i++) {
            try {
                childSemaphore.acquire();
                System.out.println("Line from parent");
                parentSemaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}