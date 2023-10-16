package ru.nsu.plodushcheva;

import java.util.*;

public class Sort implements Runnable{
    static List<String> list;
    Object listLock;

    public Sort(List<String> list, Object listLock) {
        Sort.list = list;
        this.listLock = listLock;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            bubbleSort();
        }
    }

    public static void bubbleSort() {
        int n = list.size();
        boolean swapped;
        ListIterator<String> iterator1;
        ListIterator<String> iterator2;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            iterator1 = list.listIterator();
            iterator2 = list.listIterator(1);

            for (int j = 0; j < n - 1 - i; j++) {
                String str1 = iterator1.next();
                String str2 = iterator2.next();

                if (str1.compareTo(str2) > 0) {
                    iterator1.set(str2);
                    iterator2.set(str1);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
