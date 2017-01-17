package com.tomaszrykala.dorefindmi.things.supplier.digidisplay;

import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.ht16k33.Ht16k33;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;

import java.io.IOException;

public class RealDigiDisplaySupplier implements DigiDisplaySupplier {

    private AlphanumericDisplay alphanumericDisplay;

    @Override public void init() {
        try {
            alphanumericDisplay = RainbowHat.openDisplay();
            alphanumericDisplay.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            alphanumericDisplay.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void display(double n) {
        try {
            alphanumericDisplay.display(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void display(String s) {
        try {
            alphanumericDisplay.display(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public void close() throws Exception {
        alphanumericDisplay.close();
    }
}
