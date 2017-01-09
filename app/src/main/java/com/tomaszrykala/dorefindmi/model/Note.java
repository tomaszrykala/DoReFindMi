package com.tomaszrykala.dorefindmi.model;

public enum Note {
    DO_LO("DO_LO", Led.ZERO, 0), RE("RE", Led.ONE, 1), MI("MI", Led.TWO, 2), FA("FA", Led.THREE, 3),
    SO("SO", Led.FOUR, 4), LA("LA", Led.FIVE, 5), SI("SI", Led.SIX, 6), DO_HI("DO_HI", Led.ALL, 7),
    MISS("MISS", Led.NONE, -1);

    public final String pitch;
    public final Led led;
    public final int order;

    Note(String pitch, Led led, int order) {
        this.pitch = pitch;
        this.led = led;
        this.order = order;
    }
}
