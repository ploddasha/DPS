package ru.nsu.plodushcheva;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Каждый отдел (класс Department) умеет вычислять сумму от 1 до n, где n — это
 * рандомное число от 1 до 6.
 */
public class Department {
    private final int identifier;
    private final int workingSeconds;
    private int calculationResult = 0;
    public Department(final int identifier) {
        this.identifier = identifier;
        this.workingSeconds = ThreadLocalRandom.current().nextInt(1, 6);
    }

    /**
     * Симуляция работы длительностью в workingSeconds секунд.
     * В данном случае просто вычисляем сумму.
     * Каждая итерация суммирования занимает 1 секунду. Поэтому: если n равно 3, то на
     * вычисление суммы 0 + 1 + 2 уйдёт 3 секунды (метод performCalculations).
     */
    public void performCalculations() {
        for (int i = 0; i < workingSeconds; i++) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (final InterruptedException ignored) {
                // Ignored case.
            }
            calculationResult += i;
        }
        System.out.println("Department with id " + identifier + " got " + calculationResult);
    }

    /**
     * @return Уникальный идентификатор для отдела,
     * по которому мы можем отличить его от других.
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Далеко не самый правильный способ вычисления и получения данных,
     * но для демонстрации работы барьера пойдёт.
     *
     * @return Результат вычислений.
     */
    public int getCalculationResult() {
        return calculationResult;
    }
}
