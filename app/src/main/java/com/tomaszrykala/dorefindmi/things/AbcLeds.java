package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.AbcLed;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.suppliers.abcled.AbcLedSupplier;

public class AbcLeds implements BaseController, AbcLed.Listener {

    private final AbcLedSupplier supplier;

    public AbcLeds(AbcLedSupplier abcLedSupplier) {
        supplier = abcLedSupplier;
    }

    // TODO not needed?
    public boolean isLitAt(AbcLed abcLed) {
        return supplier.isLitAt(abcLed);
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
