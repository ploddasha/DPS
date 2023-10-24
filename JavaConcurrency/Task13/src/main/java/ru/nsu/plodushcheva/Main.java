package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        int countOfPhilosophers = 5;
        Lock[] forks = new ReentrantLock[countOfPhilosophers];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new ReentrantLock();
        }
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        List<Thread> philosophers = new ArrayList<>();
        for (int i = 0; i < countOfPhilosophers; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % forks.length];
            philosophers.add(new Thread(new Philosopher(i + 1, leftFork, rightFork, lock, condition)));
        }
        philosophers.forEach(Thread::start);
    }
}