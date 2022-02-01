package ru.job4j.synch;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SingleLockListTest {

    @Test
    public void whenAddTwoElements() throws InterruptedException {
        List<Integer> in = List.of(1, 2);
        SingleLockList<Integer> list = new SingleLockList<>(in);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Set<Integer> rsl = new TreeSet<>();
        list.iterator().forEachRemaining(rsl::add);
        Assert.assertThat(rsl, Matchers.is(Set.of(1, 2)));
    }

    @Test
    public void whenAddTwoElementsThenGetFirstSuccess() throws InterruptedException {
        List<Integer> in = List.of(1, 2);
        SingleLockList<Integer> list = new SingleLockList<>(in);
        Thread first = new Thread(() -> list.add(1));
        Thread second = new Thread(() -> list.add(2));
        first.start();
        second.start();
        first.join();
        second.join();
        Integer expected = in.get(0);
        Integer actual = list.get(0);
        Assert.assertEquals(expected, actual);
    }

}