package ru.nsu.plodushcheva;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore[] semaphores = new Semaphore[4];
        for (int i = 0; i < 4; i++) {
            semaphores[i] = new Semaphore(0);
        }

        String [] parts = {"DetailA", "DetailB", "DetailC", "Module", "Widget"};
        Conveyor[] conveyors = new Conveyor[5];
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            conveyors[i] = new Conveyor(parts[i], semaphores);
            threads[i] = new Thread(conveyors[i]);
            threads[i].start();
        }
    }
}