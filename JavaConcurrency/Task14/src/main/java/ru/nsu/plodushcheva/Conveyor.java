package ru.nsu.plodushcheva;

import java.util.concurrent.Semaphore;

public class Conveyor implements Runnable{
    String part;
    Semaphore semaphoreA;
    Semaphore semaphoreB;
    Semaphore semaphoreC;

    public Conveyor(String part, Semaphore[] semaphores) {
        this.part = part;
        this.semaphoreA = semaphores[0];
        this.semaphoreB = semaphores[1];
        this.semaphoreC = semaphores[2];
    }


    @Override
    public void run() {
        while (true) {
            switch (part) {
                case "DetailA" -> {
                    System.out.println("Doing detail A");
                    try {
                        Thread.sleep(1000);
                        semaphoreA.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                case "DetailB" -> {
                    System.out.println("Doing detail B");
                    try {
                        Thread.sleep(2000);
                        semaphoreB.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                case "DetailC" -> {
                    System.out.println("Doing detail C");
                    try {
                        Thread.sleep(3000);
                        semaphoreC.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                case "Module" -> {
                    System.out.println("Doing module");
                    try {
                        semaphoreA.acquire();
                        semaphoreB.acquire();
                        System.out.println("Module has been done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                case "Widget" -> {
                    System.out.println("Doing widget");
                    try {
                        semaphoreC.acquire();
                        System.out.println("Widget has been done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
