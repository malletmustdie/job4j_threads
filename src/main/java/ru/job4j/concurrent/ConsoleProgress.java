package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] arr = {"-", "\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            print(arr);
        }
    }

    private void print(String[] strings) {
        try {
            for (String s : strings) {
                System.out.print("\r load: " + s);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(3000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
