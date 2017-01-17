package com.tomaszrykala.dorefindmi.things.supplier.abcleds;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

public class RealAbcLedsSupplier implements AbcLedsSupplier {

    private Gpio ledA;
    private Gpio ledB;
    private Gpio ledC;

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

    @Override
    public void close() throws Exception {
        ledA.close();
        ledB.close();
        ledC.close();
    }
}
