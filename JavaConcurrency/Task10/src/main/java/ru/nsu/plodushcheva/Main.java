package ru.nsu.plodushcheva;

public class Main {

    public static void main(String[] args) {
        final Object lock = new Object();
        final boolean[] flag = {false};

        Thread childThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    try {
                        while (!flag[0]) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Line from child");
                synchronized (lock) {
                    flag[0] = false;
                    lock.notify();
                }
            }
        });

        childThread.start();

        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                try {
                    while (flag[0]) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Line from parent");
            synchronized (lock) {
                flag[0] = true;
                lock.notify();
            }
        }

    }
}
