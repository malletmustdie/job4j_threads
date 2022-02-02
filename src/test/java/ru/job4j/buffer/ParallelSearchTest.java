package ru.job4j.buffer;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

public class ParallelSearchTest {

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);

        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(queue::offer)
        );

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        producer.start();
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();

        Assert.assertEquals(5, buffer.size());
        Assert.assertEquals(0, queue.size());
        Assert.assertThat(List.of(0, 1, 2, 3, 4), Matchers.is(buffer));
    }

}