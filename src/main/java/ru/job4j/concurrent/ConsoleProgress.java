package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] arr = {"-", "\\", "|", "/"};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                for (int i = 0; i < arr.length; i++) {
                    if (i == arr.length - 1) {
                        i = 0;
                    }
                    System.out.print("\r load: " + arr[i]);
                    Thread.sleep(300);
                }
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
