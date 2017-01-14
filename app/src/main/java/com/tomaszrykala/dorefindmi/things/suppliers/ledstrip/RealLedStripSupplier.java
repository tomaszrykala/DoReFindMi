package com.tomaszrykala.dorefindmi.things.suppliers.ledstrip;

import android.graphics.Color;

import com.google.android.things.contrib.driver.apa102.Apa102;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.tomaszrykala.dorefindmi.model.Led;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RealLedStripSupplier implements LedStripSupplier {

    private static final int MAX_BRIGHTNESS = 15; // max = 31
    private static final int NO_OF_LEDS = 7;

    private final Map<Led, Boolean> ledColorHashMap = new HashMap<>(NO_OF_LEDS);
    private Apa102 apa102;

    public RealLedStripSupplier() {
        putAll(false);
        init();
    }

    @Override public void init() {
        try {
            apa102 = RainbowHat.openLedStrip();
            apa102.setBrightness(MAX_BRIGHTNESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        reset();
    }

    @Override public boolean isLitAt(Led led) {
        return ledColorHashMap.get(led);
    }

    @Override public void light(Led led) {
        if (led == Led.ALL) {
            putAll(true);
        } else {
            ledColorHashMap.put(led, true);
        }
        light();
    }

    @Override public void reset() {
        putAll(false);
        light();
    }

    private void light() {
        final int[] colors = new int[NO_OF_LEDS];
        for (Led led : ledColorHashMap.keySet()) {
            colors[led.index] = ledColorHashMap.get(led) ? led.color : Color.BLACK;
        }

        try {
            apa102.write(colors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putAll(boolean all) {
        ledColorHashMap.put(Led.ZERO, all);
        ledColorHashMap.put(Led.ONE, all);
        ledColorHashMap.put(Led.TWO, all);
        ledColorHashMap.put(Led.THREE, all);
        ledColorHashMap.put(Led.FOUR, all);
        ledColorHashMap.put(Led.FIVE, all);
        ledColorHashMap.put(Led.SIX, all);
    }

    @Override public void close() throws Exception {
        apa102.close();
    }
}
