package ru.nsu.plodushcheva;

public class Main {

    public static void main(String[] args) {
        final Object lock = new Object();
        final int[] flag = {0};

        Thread childThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    try {
                        while (flag[0] == 0) {
                            lock.wait();
                        }
                        System.out.println("Line from child");
                        flag[0] = 0;
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        childThread.start();

        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                try {
                    while (flag[0] == 1) {
                        lock.wait();
                    }
                    System.out.println("Line from parent");
                    flag[0] = 1;
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
