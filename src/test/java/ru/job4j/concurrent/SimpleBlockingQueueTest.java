package ru.job4j.concurrent;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {

    @Test
    public void whenAdd10NumberThenReturn10Numbers() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        List<Integer> resultList = new ArrayList<>();

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        queue.offer(i);
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        try {
                            resultList.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        Assert.assertEquals(10, resultList.size());
        Assert.assertEquals(0, queue.size());
        Assert.assertThat(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9), Matchers.is(resultList));
    }

}