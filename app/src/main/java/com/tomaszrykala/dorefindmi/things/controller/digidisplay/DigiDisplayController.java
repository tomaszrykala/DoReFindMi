package com.tomaszrykala.dorefindmi.things.controller.digidisplay;

import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.suppliers.digidisplay.DigiDisplaySupplier;

public class DigiDisplayController implements BaseController, Timer.Listener {

    private final DigiDisplaySupplier supplier;

    private boolean isRunning;
    private long counter;

    public DigiDisplayController(DigiDisplaySupplier digiDisplaySupplier) {
        supplier = digiDisplaySupplier;
        supplier.init();
    }

    @Override public void onCounter(int counter) {
        synchronized (this) {
            supplier.display(counter);
        }
        this.counter = counter;
    }

    @Override public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long get() {
        return counter;
    }

    public void displayBlocking(String text, int millis) {
        synchronized (this) {
            supplier.display(text);
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void display(String text) {
        synchronized (this) {
            supplier.display(text);
        }
    }

    @Override public void close() throws Exception {
        synchronized (this) {
            supplier.close();
        }
    }
}
