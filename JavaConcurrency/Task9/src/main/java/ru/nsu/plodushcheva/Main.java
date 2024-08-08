package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int COUNT_OF_PHILOSOPHERS = 5;


    public static void main(String[] args) {

        List<Thread> philosophers = new ArrayList<>();
        Object[] forks = new Object[COUNT_OF_PHILOSOPHERS];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }
        for (int i = 0; i < COUNT_OF_PHILOSOPHERS; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            // resolve deadlock - last philosopher picks up the right fork first
            if (i == COUNT_OF_PHILOSOPHERS- 1) {
                philosophers.add(new Thread(new Philosopher(i + 1, rightFork, leftFork)));
            } else {
                philosophers.add(new Thread(new Philosopher(i + 1, leftFork, rightFork)));
            }
        }
        philosophers.forEach(Thread::start);
    }
}
