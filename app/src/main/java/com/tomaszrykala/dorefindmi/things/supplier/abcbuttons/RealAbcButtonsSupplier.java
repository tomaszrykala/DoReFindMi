package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.tomaszrykala.dorefindmi.model.AbcButton;

import java.io.IOException;

public class RealAbcButtonsSupplier implements AbcButtonsSupplier {

    private Button buttonA;
    private Button buttonB;
    private Button buttonC;

    private Listener listener;

    public RealAbcButtonsSupplier() {
        init();
    }

    @Override
    public void init() {
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
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override public void onButtonEvent(Button button, boolean pressed) {
        if (button == buttonA) {
            listener.onButtonEvent(AbcButton.A, pressed);
        } else if (button == buttonB) {
            listener.onButtonEvent(AbcButton.B, pressed);
        } else if (button == buttonC) {
            listener.onButtonEvent(AbcButton.C, pressed);
        }
    }

    @Override public void close() throws Exception {
        buttonA.close();
        buttonB.close();
        buttonC.close();
    }
}
