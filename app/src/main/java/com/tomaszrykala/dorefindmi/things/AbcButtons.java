package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.suppliers.abcbutton.AbcButtonSupplier;

public class AbcButtons implements BaseController, AbcButtonSupplier.Listener {

    private final AbcButtonSupplier supplier;
    private AbcButton.Listener listener;

    private boolean isEnabled;
    private AbcButton lastPressed;

    public void setListener(AbcButton.Listener listener) {
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
                    listener.onAbcButton(AbcButton.A);
                    break;
                case B:
                    listener.onAbcButton(AbcButton.B);
                    break;
                case C:
                    listener.onAbcButton(AbcButton.C);
                    break;
            }
        }
    }

    @Override public void close() throws Exception {
        supplier.close();
    }

}
