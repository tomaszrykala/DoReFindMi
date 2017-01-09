package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.game.Timer;

public class DigitalDisplay implements Timer.Listener {

    private boolean isRunning;
    private int counter;

    @Override public void onCounter(int counter) {
        this.counter = counter;
    }

    @Override public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int get() {
        return counter;
    }
}
