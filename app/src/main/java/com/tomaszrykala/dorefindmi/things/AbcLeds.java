package com.tomaszrykala.dorefindmi.things;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.AbcLed;
import com.tomaszrykala.dorefindmi.things.suppliers.abcled.AbcLedSupplier;

import java.io.IOException;

public class AbcLeds implements AbcLed.Listener, AutoCloseable {

    private final AbcLedSupplier supplier;
    private AbcLed lastLit;

    public boolean isLitAt(AbcLed abcLed) {
        return lastLit == abcLed;
    }

    public AbcLeds(AbcLedSupplier abcLedSupplier) {
        supplier = abcLedSupplier;
    }

    @Override
    public void lightFor(AbcButton abcButton) {
        boolean onPadA = false;
        boolean onPadB = false;
        boolean onPadC = false;
        switch (abcButton) {
            case A:
                onPadA = true;
                break;
            case B:
                onPadB = true;
                break;
            case C:
                onPadC = true;
                break;
        }
        lightFor(onPadA, onPadB, onPadC);
    }

    @Override
    public void reset() {
        lightFor(false, false, false);
    }

    private void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        supplier.lightFor(onPadA, onPadB, onPadC);
    }

    @Override
    public void close() throws Exception {
        supplier.close();
    }

}
