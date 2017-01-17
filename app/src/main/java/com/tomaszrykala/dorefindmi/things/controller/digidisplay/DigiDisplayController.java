package com.tomaszrykala.dorefindmi.things.controller.digidisplay;

import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplier;

public class DigiDisplayController implements BaseController, Timer.Listener {

    private final DigiDisplaySupplier supplier;
    private boolean isRunning;
    private double counter;

    public DigiDisplayController(DigiDisplaySupplier digiDisplaySupplier) {
        supplier = digiDisplaySupplier;
        supplier.init();
    }

    @Override public void onTick(double tick) {
        counter = tick;
        display(counter);
    }

    @Override public void onStart() {
        isRunning = true;
    }

    @Override public void onStop() {
        isRunning = false;
    }

    @Override public void close() throws Exception {
        supplier.close();
    }

    public void display(double n) {
        supplier.display(n);
    }

    public void displayBlocking(String text, int millis) {
        supplier.display(text);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public double getCounter() {
        return counter;
    }
}
