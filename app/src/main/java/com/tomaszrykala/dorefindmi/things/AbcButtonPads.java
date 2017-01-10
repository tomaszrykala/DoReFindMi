package com.tomaszrykala.dorefindmi.things;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.tomaszrykala.dorefindmi.game.PadListener;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.io.IOException;

public class AbcButtonPads implements Button.OnButtonEventListener, AutoCloseable {

    private PadListener listener;

    private boolean isEnabled;
    private Pad lastPressed;

    public AbcButtonPads() {
        this(false); // TODO
    }

    public AbcButtonPads(boolean init) {
        if (init) {
            initButtons();
        }
    }

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

    public boolean isLastPressed(Pad pad) {
        return lastPressed == pad;
    }

    public void setLastPressed(Pad pad) {
        lastPressed = pad;
    }

    public boolean hasLastPressed() {
        return lastPressed != null;
    }

    private Button buttonA;
    private Button buttonB;
    private Button buttonC;

    @Override public void onButtonEvent(Button button, boolean pressed) {
        if (pressed) {
            if (button == buttonA) {
                listener.onPad(Pad.A);
            } else if (button == buttonB) {
                listener.onPad(Pad.B);
            } else if (button == buttonC) {
                listener.onPad(Pad.C);
            }
        }
    }

    private void initButtons() {
        try {
            buttonA = RainbowHat.openButton(RainbowHat.BUTTON_A);
            buttonB = RainbowHat.openButton(RainbowHat.BUTTON_B);
            buttonC = RainbowHat.openButton(RainbowHat.BUTTON_C);

            buttonA.setOnButtonEventListener(this);
            buttonB.setOnButtonEventListener(this);
            buttonC.setOnButtonEventListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        buttonA.close();
        buttonB.close();
        buttonC.close();
    }
}
