package ru.nsu.plodushcheva;

public class Philosopher implements Runnable{
    private final int id;
    private final Object leftFork;
    private final Object rightFork;

    private static final int MAX_EATING_TIME = 2000; // in milliseconds
    private static final int MAX_THINKING_TIME = 200; // in milliseconds
    private static final int ACTION_TIME = 100; // in milliseconds


    public Philosopher(int id, Object leftFork, Object rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void eat() {
        System.out.println("Philosopher " + id + ": Eating");
        long eating = (long) (Math.random() * MAX_EATING_TIME);
        try {
            Thread.sleep(eating);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void think() {
        System.out.println("Philosopher " + id + ": Thinking");
        try {
            Thread.sleep((long) (Math.random() * MAX_THINKING_TIME));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println("Philosopher " + id + ": " + action);
        Thread.sleep(((int) (Math.random() * ACTION_TIME)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                synchronized (leftFork) {
                    doAction("Picked up left fork");
                    synchronized (rightFork) {
                        doAction("Picked up right fork");
                        eat();
                        doAction("Put down right fork");
                    }
                    doAction("Put down left fork. Back to thinking");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
