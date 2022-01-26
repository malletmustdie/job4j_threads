package ru.job4j.concurrent;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class WgetDownload implements Runnable {

    private final String url;
    private final int speed;
    private final String targetFile;

    public WgetDownload(String url, int speed, String targetFile) {
        this.url = url;
        this.speed = speed;
        this.targetFile = targetFile;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(targetFile)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long diff = System.currentTimeMillis() - start;
                if (diff < speed) {
                    Thread.sleep(speed - diff);
                }
                start = System.currentTimeMillis();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2) {
            throw new InterruptedException();
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String targetFile = args[2];
        Thread wget = new Thread(new WgetDownload(url, speed, targetFile));
        wget.start();
        wget.join();
    }

}
