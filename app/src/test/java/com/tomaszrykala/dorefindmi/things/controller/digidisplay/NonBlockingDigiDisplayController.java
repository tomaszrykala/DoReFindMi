package com.tomaszrykala.dorefindmi.things.controller.digidisplay;

import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplier;

public class NonBlockingDigiDisplayController extends DigiDisplayController {

    public NonBlockingDigiDisplayController(DigiDisplaySupplier digiDisplaySupplier) {
        super(digiDisplaySupplier);
    }

    @Override public void displayBlocking(String text, int millis) {
        super.displayBlocking(text, 0);
    }
}
