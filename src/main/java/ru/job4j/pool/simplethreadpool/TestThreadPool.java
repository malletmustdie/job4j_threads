package ru.job4j.pool.simplethreadpool;

public class TestThreadPool {

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                int taskNo = i;
                pool.work(() -> {
                    String message = Thread.currentThread().getName() + ": Task " + taskNo;
                    System.out.println(message);
                });
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        pool.shutdown();
    }

}
