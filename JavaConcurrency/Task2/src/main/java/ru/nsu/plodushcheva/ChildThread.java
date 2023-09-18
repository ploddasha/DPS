package ru.nsu.plodushcheva;

public class ChildThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Line from child");
        }
    }
}
