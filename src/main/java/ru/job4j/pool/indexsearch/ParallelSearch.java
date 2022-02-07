package ru.job4j.pool.indexsearch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int low;
    private final int high;
    private final T key;

    public ParallelSearch(T[] array, int low, int high, T key) {
        this.array = array;
        this.low = low;
        this.high = high;
        this.key = key;
    }

    public static <T> Integer search(T[] array, T value) {
        ForkJoinPool pool = new ForkJoinPool();
        ParallelSearch<T> search = new ParallelSearch<>(array, 0, array.length, value);
        return pool.invoke(search);
    }

    @Override
    protected Integer compute() {
        if (high - low <= 10) {
            return findIndex();
        }
        int middle = (low + high) / 2;
        ParallelSearch<T> leftSearch = new ParallelSearch<>(array, low, middle - 1, key);
        ParallelSearch<T> rightSearch = new ParallelSearch<>(array, middle + 1, high, key);
        leftSearch.fork();
        rightSearch.fork();
        int valueFromLeft = leftSearch.join();
        int valueFromRight = rightSearch.join();
        return Math.max(valueFromLeft, valueFromRight);
    }

    private Integer findIndex() {
        int index = -1;
        for (int i = low; i < high; i++) {
            if (key.equals(array[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

}

