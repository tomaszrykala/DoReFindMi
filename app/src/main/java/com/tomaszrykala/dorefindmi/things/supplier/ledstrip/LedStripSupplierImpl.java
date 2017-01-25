package com.tomaszrykala.dorefindmi.things.supplier.ledstrip;

import android.graphics.Color;
import android.support.annotation.NonNull;

import com.google.android.things.contrib.driver.apa102.Apa102;
import com.tomaszrykala.dorefindmi.model.Led;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.android.things.contrib.driver.rainbowhat.RainbowHat.BUS_LEDSTRIP;
import static com.google.android.things.contrib.driver.rainbowhat.RainbowHat.LEDSTRIP_LENGTH;

public class LedStripSupplierImpl implements LedStripSupplier {

    private static final int MAX_BRIGHTNESS = 7; // max = 31

    private final Map<Led, Boolean> ledColorHashMap = new HashMap<>(LEDSTRIP_LENGTH);
    private Apa102 apa102;

    @Override public void init() {
        try {
            apa102 = openLedStrip();
            apa102.setBrightness(MAX_BRIGHTNESS);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        final int[] colors = new int[LEDSTRIP_LENGTH];
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

    /**
     * No Direction.REVERSED option in RainbowHAT driver factory.
     */
    @NonNull private Apa102 openLedStrip() throws IOException {
        return new Apa102(BUS_LEDSTRIP, Apa102.Mode.BGR, Apa102.Direction.REVERSED);
    }
}
