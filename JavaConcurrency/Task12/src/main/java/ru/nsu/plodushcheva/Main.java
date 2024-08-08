package ru.nsu.plodushcheva;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new LinkedList<>();
        final Object listLock = new Object();

        Thread sortThread = new Thread(new Sort(list, listLock));
        sortThread.start();


        while (true) {
            String string = scanner.nextLine();
            if (string.isEmpty()) {
                synchronized (listLock) {
                    System.out.println("List size: " + list.size());
                    for (String str : list) {
                        System.out.println(str);
                    }
                }
            } else {
                synchronized (listLock) {
                    if (string.length() > 80) {
                        int index = 0;
                        while (index < string.length()) {
                            String subString = string.substring(index, Math.min(index + 80, string.length()));
                            list.add(0, subString);
                            index += 80;
                        }
                    } else {
                        list.add(0, string);
                    }
                }
            }
        }
    }
}