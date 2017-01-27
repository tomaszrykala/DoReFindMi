package com.tomaszrykala.dorefindmi.model;

import static com.tomaszrykala.dorefindmi.model.Led.ALL;
import static com.tomaszrykala.dorefindmi.model.Led.FIVE;
import static com.tomaszrykala.dorefindmi.model.Led.FOUR;
import static com.tomaszrykala.dorefindmi.model.Led.NONE;
import static com.tomaszrykala.dorefindmi.model.Led.ONE;
import static com.tomaszrykala.dorefindmi.model.Led.SIX;
import static com.tomaszrykala.dorefindmi.model.Led.THREE;
import static com.tomaszrykala.dorefindmi.model.Led.TWO;
import static com.tomaszrykala.dorefindmi.model.Led.ZERO;
import static com.tomaszrykala.pianokeys.Key.A4;
import static com.tomaszrykala.pianokeys.Key.B4;
import static com.tomaszrykala.pianokeys.Key.C3;
import static com.tomaszrykala.pianokeys.Key.C4;
import static com.tomaszrykala.pianokeys.Key.C5;
import static com.tomaszrykala.pianokeys.Key.D4;
import static com.tomaszrykala.pianokeys.Key.E4;
import static com.tomaszrykala.pianokeys.Key.F4;
import static com.tomaszrykala.pianokeys.Key.G4;

public enum Note {
    DO_LO(ZERO, 0, C4), RE(ONE, 1, D4), MI(TWO, 2, E4), FA(THREE, 3, F4), SO(FOUR, 4, G4),
    LA(FIVE, 5, A4), SI(SIX, 6, B4), DO_HI(ALL, 7, C5), MISS(NONE, -1, C3);

    public final Led led;
    public final int order;
    public final double pitch;

    Note(Led led, int order, double pitch) {
        this.led = led;
        this.order = order;
        this.pitch = pitch;
    }
}
