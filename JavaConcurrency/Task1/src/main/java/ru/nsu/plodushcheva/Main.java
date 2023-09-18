package ru.nsu.plodushcheva;

public class Main {
    public static void main(String []args){
        Thread thread = new ChildThread();
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("Line from parent");
        }
    }
}
