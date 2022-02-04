package ru.job4j.pool.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(() -> {
            try {
                String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
                String body = String.format("Add new event to %s", user.getUsername());
                send(subject, body, user.getEmail());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        });
    }

    public void send(String subject, String body, String email) throws InterruptedException {
        System.out.println("Send email to: " + subject + " with body " + body);
        for (int i = 1; i <= 3; i++) {
            System.out.println(i);
            Thread.sleep(1000);
        }
        System.out.println("Successful send to " + email);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        EmailNotification notification = new EmailNotification();
        Thread thread = new Thread(
                () -> {
                    User user = new User("malletmustdie", "napaslavandos@gmail.com");
                    notification.emailTo(user);
                }
        );
        thread.start();
        thread.join();
        notification.close();
    }

}
