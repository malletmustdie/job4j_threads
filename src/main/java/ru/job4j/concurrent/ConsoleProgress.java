package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] arr = {"-", "\\", "|", "/"};
        int index = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + arr[index++]);
                if (index == arr.length) {
                    index = 0;
                }
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
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
