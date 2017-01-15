package com.tomaszrykala.dorefindmi.game;

import java.util.concurrent.TimeUnit;

public class Timer {

    public interface Listener {
        void onCounter(int counter);

        void onStart();

        void onStop();
    }

    private int counter;
    private boolean isRunning;
    private final Listener listener;

    private final Thread thread = new Thread(new Runnable() {
        @Override public void run() {
            while (isRunning) {
                counter++;
                listener.onCounter(counter);
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException ignored) {}
            }
        }
    });

    public Timer(Listener listener) {
        this.listener = listener;
    }

    void start() {
        counter = 0;
        isRunning = true;
        listener.onStart();
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    void stop() {
        thread.interrupt();
        listener.onStop();
        isRunning = false;
    }

    public int getCounter() {
        return counter;
    }
}
