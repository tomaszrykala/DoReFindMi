package com.tomaszrykala.dorefindmi;

import java.util.LinkedHashSet;
import java.util.Set;

class LedStrip {

    private final Set<Led> leds = new LinkedHashSet<>();

    public boolean isLitAt(Led led) {
        return leds.contains(led);
    }

    public void light(Led led) {
        leds.add(led);
    }
}
