package com.tomaszrykala.dorefindmi.things.controller.digidisplay;

import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplier;

public class DigiDisplayController implements BaseController, Timer.Listener {

    private final DigiDisplaySupplier supplier;
    private boolean isRunning;
    private long counter;

    public DigiDisplayController(DigiDisplaySupplier digiDisplaySupplier) {
        supplier = digiDisplaySupplier;
        supplier.init();
    }

    @Override public void onCounter(int counter) {
        synchronized (supplier) {
            supplier.display(counter);
            this.counter = counter;
        }
    }

    @Override public void onStart() {
        isRunning = true;
    }

    @Override public void onStop() {
        isRunning = false;
    }

    @Override public void close() throws Exception {
        synchronized (supplier) {
            supplier.close();
        }
    }

    public void displayBlocking(String text, int millis) {
        synchronized (supplier) {
            supplier.display(text);
            try {
                Thread.sleep(millis);
            } catch (InterruptedException ignored) {}
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getCounter() {
        return counter;
    }
}
