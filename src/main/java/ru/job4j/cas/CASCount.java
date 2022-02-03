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
        var ref = count.get();
        if (ref == null) {
            throw new UnsupportedOperationException("Count is not impl.");
        }
        return ref;
    }

}
