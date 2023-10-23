package ru.nsu.plodushcheva;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private final Lock forks;
    private final Condition condition;

    public Philosopher(int id, Lock leftFork, Lock rightFork, Lock lock, Condition condition) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.forks = lock;
        this.condition = condition;
    }

    private void eat() {
        System.out.println("Philosopher " + id + ": Eating");
        long eating = (long) (Math.random() * 1000);
        try {
            Thread.sleep(eating);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() {
        System.out.println("Philosopher " + id + ": Thinking");
        try {
            Thread.sleep((long) (Math.random() * 200));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println("Philosopher " + id + ": " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        while (true) {
            think();

            forks.lock();
            try {
                if (leftFork.tryLock()) {
                    doAction("Picked up left fork");
                    if (rightFork.tryLock()) {
                        doAction("Picked up right fork");
                    } else {
                        leftFork.unlock();
                        continue;
                    }
                } else {
                    continue;
                }
            } catch (InterruptedException e) {
                System.out.println("Something happened to philosopher " + id);
            } finally {
                forks.unlock();
            }

            eat();

            forks.lock();
            try {
                rightFork.unlock();
                doAction("Put down right fork");

                leftFork.unlock();
                doAction("Put down left fork. Back to thinking");

                condition.signalAll();

            } catch (InterruptedException e) {
                System.out.println("something happened to philosopher $id");
            } finally {
                forks.unlock();
            }

        }
    }
}
