package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.Led;

import java.util.LinkedHashSet;
import java.util.Set;

public class LedStrip {

    private final Set<Led> leds = new LinkedHashSet<>();

    public boolean isLitAt(Led led) {
        return leds.contains(led);
    }

    public void light(Led led) {
        leds.add(led);
    }
}
