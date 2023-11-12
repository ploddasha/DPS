package ru.nsu.plodushcheva;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {
        final Object lock = new Object();
        final AtomicBoolean flag = new AtomicBoolean(false);

        Thread childThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    try {
                        while (!flag.get()) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Line from child");
                synchronized (lock) {
                    flag.set(false);
                    lock.notify();
                }
            }
        });

        childThread.start();

        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                try {
                    while (flag.get()) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Line from parent");
            synchronized (lock) {
                flag.set(true);
                lock.notify();
            }
        }
    }
}
