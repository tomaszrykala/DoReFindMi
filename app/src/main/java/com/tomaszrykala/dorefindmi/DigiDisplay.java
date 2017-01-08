package com.tomaszrykala.dorefindmi;

class DigiDisplay implements Timer.Listener {

    private static final String TAG = DigiDisplay.class.getSimpleName();

    private boolean isRunning;
    private int counter;

    @Override public void onCounter(int counter) {
        System.out.println("counter = [" + counter + "]");
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
