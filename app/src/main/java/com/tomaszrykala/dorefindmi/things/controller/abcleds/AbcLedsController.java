package com.tomaszrykala.dorefindmi.things.controller.abcleds;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.AbcLed;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.AbcLedsSupplier;

public class AbcLedsController implements BaseController, AbcLed.Listener {

    private final boolean[] lit = new boolean[]{false, false, false};

    private final AbcLedsSupplier supplier;

    public AbcLedsController(AbcLedsSupplier abcLedsSupplier) {
        supplier = abcLedsSupplier;
    }

    public boolean isLitAt(AbcLed abcLed) {
        switch (abcLed) {
            case A:
                return lit[0];
            case B:
                return lit[1];
            case C:
                return lit[2];
            default:
                return false;
        }
    }

    @Override
    public void lightFor(AbcButton abcButton) {
        switch (abcButton) {
            case A:
                setLitLeds(true, false, false);
                break;
            case B:
                setLitLeds(false, true, false);
                break;
            case C:
                setLitLeds(false, false, true);
                break;
        }
        lightFor(lit[0], lit[1], lit[2]);
    }

    @Override
    public void reset() {
        setLitLeds(false, false, false);
        lightFor(lit[0], lit[1], lit[2]);
    }

    @Override
    public void close() throws Exception {
        supplier.close();
    }

    private void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        supplier.lightFor(onPadA, onPadB, onPadC);
    }

    private void setLitLeds(boolean zero, boolean one, boolean two) {
        lit[0] = zero;
        lit[1] = one;
        lit[2] = two;
    }
}
