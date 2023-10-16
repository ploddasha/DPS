package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int countOfPhilosophers = 5;
        List<Thread> philosophers = new ArrayList<>();
        Object[] forks = new Object[countOfPhilosophers];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }
        for (int i = 0; i < countOfPhilosophers; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            // resolve deadlock - last philosopher picks up the right fork first
            if (i == countOfPhilosophers- 1) {
                philosophers.add(new Thread(new Philosopher(i + 1, rightFork, leftFork)));
            } else {
                philosophers.add(new Thread(new Philosopher(i + 1, leftFork, rightFork)));
            }
        }
        philosophers.forEach(Thread::start);
    }
}
