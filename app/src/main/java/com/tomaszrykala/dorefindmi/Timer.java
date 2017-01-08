package com.tomaszrykala.dorefindmi;

import java.util.concurrent.TimeUnit;

class Timer {

    Timer(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onCounter(int counter);

        void setIsRunning(boolean isRunning);
    }

    private int counter;
    private boolean run;
    private Thread thread;
    private final Listener listener;

    public void start() {
        run = true;
        counter = 0;
        listener.setIsRunning(true);
        thread = new Thread(new Runnable() {
            @Override public void run() {
                while (run) {
                    counter++;
                    listener.onCounter(counter);
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public void stop() {
        if (thread == null || thread.isInterrupted()) {
            throw new IllegalStateException("Not started yet");
        } else {
            listener.setIsRunning(false);
            // thread.interrupt(); // TODO: ?
            run = false;
        }
    }

    public int get() {
        return counter;
    }
}
