package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {

    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        int value;
        int incrValue;
        do {
            value = get();
            incrValue = value++;
        } while (!count.compareAndSet(value, incrValue));
    }

    public int get() {
        return count.get();
    }

}
