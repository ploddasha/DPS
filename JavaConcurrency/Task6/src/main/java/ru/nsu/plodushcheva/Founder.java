package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 1. Список со всеми Worker’ами (Runnable).
 * 2. За каждым Worker’ом закреплён свой Department.
 * 3. Каждый Worker инициирует запуск вычислений.
 * 4. После того как во всех нитях завершатся вычисления, выводится результат (метод
 * showCollaborativeResult)
 */
public final class Founder {

    private final CyclicBarrier cyclicBarrier;
    private final List<Worker> workers;
    private final Company company;

    public Founder(final Company company) {
        this.company = company;
        this.workers = new ArrayList<>(company.getDepartmentsCount());
        cyclicBarrier = new CyclicBarrier(company.getDepartmentsCount(), () -> {
            company.showCollaborativeResult();
            System.out.println("All threads reached the barrier");
        });

    }

    public void start() {
        for (int i = 0; i < company.getDepartmentsCount(); i++) {
            workers.add(new Worker(company.getFreeDepartment(i)));
        }

        for (final Worker worker : workers) {
            new Thread(worker).start();
        }
    }

    class Worker implements Runnable {
        Department department;

        public Worker(Department department) {
            this.department = department;
        }

        @Override
        public void run() {

            department.performCalculations();

            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                // ...
            }
        }
    }
}
