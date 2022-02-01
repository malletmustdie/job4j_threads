package ru.job4j.jcip.count;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class CountTest {

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(count.get(), Matchers.is(2));
    }

    /**
     * Класс описывает нить со счетчиком.
     */
    private static class ThreadCount extends Thread {

        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }

    }

}