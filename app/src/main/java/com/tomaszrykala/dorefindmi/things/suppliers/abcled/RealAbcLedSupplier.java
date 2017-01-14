package com.tomaszrykala.dorefindmi.things.suppliers.abcled;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;
import com.tomaszrykala.dorefindmi.model.AbcLed;

import java.io.IOException;

public class RealAbcLedSupplier implements AbcLedSupplier {

    private Gpio ledA;
    private Gpio ledB;
    private Gpio ledC;

    public RealAbcLedSupplier() {
        init();
    }

    @Override
    public void init() {
        try {
            ledA = RainbowHat.openLed(RainbowHat.LED_RED);
            ledB = RainbowHat.openLed(RainbowHat.LED_GREEN);
            ledC = RainbowHat.openLed(RainbowHat.LED_BLUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        try {
            ledA.setValue(onPadA);
            ledB.setValue(onPadB);
            ledC.setValue(onPadC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public boolean isLitAt(AbcLed abcLed) {
        try {
            switch (abcLed) {
                case A:
                    return ledA.getValue();
                case B:
                    return ledB.getValue();
                case C:
                    return ledC.getValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override public void close() throws Exception {
        ledA.close();
        ledB.close();
        ledC.close();
    }
}
