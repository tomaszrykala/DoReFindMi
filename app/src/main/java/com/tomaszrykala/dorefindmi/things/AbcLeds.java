package com.tomaszrykala.dorefindmi.things;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.AbcLed;

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

    public interface AbcLedSupplier extends AutoCloseable {

        void lightFor(boolean onPadA, boolean onPadB, boolean onPadC);
    }

    public static class MockAbcLedSupplier implements AbcLedSupplier {

        @Override public void close() throws Exception {
            System.out.println("MockAbcLedSupplier.close");
        }

        @Override public void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
            System.out.println("MockAbcLedSupplier.lightFor");
        }
    }

    public static class RealAbcLedSupplier implements AbcLedSupplier {

        private Gpio ledA;
        private Gpio ledB;
        private Gpio ledC;

        public RealAbcLedSupplier() {
            init();
        }

        private void init() {
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

        @Override public void close() throws Exception {
            ledA.close();
            ledB.close();
            ledC.close();
        }
    }
}
