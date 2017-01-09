package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.Pad;

public class AbcButtonsPad {

    private boolean isEnabled;
    private Pad lastPressed;

    public void enable() {
        isEnabled = true;
    }

    public void disable() {
        isEnabled = false;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isLastPressed(Pad pad) {
        return lastPressed == pad;
    }

    public void setLastPressed(Pad pad) {
        lastPressed = pad;
    }

    public boolean hasLastPressed() {
        return lastPressed != null;
    }
}
