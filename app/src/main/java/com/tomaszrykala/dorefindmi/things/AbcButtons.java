package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.game.PadListener;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.suppliers.abcbutton.AbcButtonSupplier;

public class AbcButtons implements AbcButton.Listener, AutoCloseable {

    private AbcButtonSupplier supplier;
    private PadListener listener;

    private boolean isEnabled;
    private AbcButton lastPressed;

    public void setListener(PadListener listener) {
        this.listener = listener;
    }

    public void enable() {
        isEnabled = true;
    }

    public void disable() {
        isEnabled = false;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isLastPressed(AbcButton abcButton) {
        return lastPressed == abcButton;
    }

    public void setLastPressed(AbcButton abcButton) {
        lastPressed = abcButton;
    }

    public boolean hasLastPressed() {
        return lastPressed != null;
    }

    public AbcButtons(AbcButtonSupplier abcButtonSupplier) {
        supplier = abcButtonSupplier;
        supplier.setListener(this);
    }

    @Override public void onButtonEvent(AbcButton abcButton, boolean pressed) {
        if (pressed) {
            switch (abcButton) {
                case A:
                    listener.onPad(AbcButton.A);
                    break;
                case B:
                    listener.onPad(AbcButton.B);
                    break;
                case C:
                    listener.onPad(AbcButton.C);
                    break;
            }
        }
    }

    @Override public void close() throws Exception {
        supplier.close();
        supplier = null;
    }

}
