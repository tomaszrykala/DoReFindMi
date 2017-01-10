package com.tomaszrykala.dorefindmi.things;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.tomaszrykala.dorefindmi.model.Gpio;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.io.IOException;

public class AbcButtonLeds implements AutoCloseable {

    private Gpio lastLit;

    public AbcButtonLeds() {
        this(false); // TODO
    }

    public AbcButtonLeds(boolean init) {
        if (init) {
            initLeds();
        }
    }

    public boolean isLitAt(Gpio gpio) {
        return lastLit == gpio;
    }

    private com.google.android.things.pio.Gpio ledA;
    private com.google.android.things.pio.Gpio ledB;
    private com.google.android.things.pio.Gpio ledC;

    public void lightFor(Pad pad) {
        boolean onPadA = false;
        boolean onPadB = false;
        boolean onPadC = false;
        switch (pad) {
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

    public void reset() {
        lightFor(false, false, false);
    }

    private void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        try {
            ledA.setValue(onPadA);
            ledB.setValue(onPadB);
            ledC.setValue(onPadC);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLeds() {
        try {
            ledA = RainbowHat.openLed(RainbowHat.LED_RED);
            ledB = RainbowHat.openLed(RainbowHat.LED_GREEN);
            ledC = RainbowHat.openLed(RainbowHat.LED_BLUE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void initLeds() {
//        try {
//            ledA = RainbowHat.openLed(RainbowHat.LED_RED);
//            ledB = RainbowHat.openLed(RainbowHat.LED_GREEN);
//            ledC = RainbowHat.openLed(RainbowHat.LED_BLUE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void close() throws Exception {
        ledA.close();
        ledB.close();
        ledC.close();
    }
}
