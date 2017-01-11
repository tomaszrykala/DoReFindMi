package com.tomaszrykala.dorefindmi.things;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.tomaszrykala.dorefindmi.game.PadListener;
import com.tomaszrykala.dorefindmi.model.AbcButton;

import java.io.IOException;

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

    public interface AbcButtonSupplier extends AutoCloseable, Button.OnButtonEventListener {
        void setListener(AbcButton.Listener listener);
    }

    public static class MockAbcButtonSupplier implements AbcButtonSupplier {

        @Override public void setListener(AbcButton.Listener listener) {
            System.out.println("MockAbcButtonSupplier.setListener");
        }

        @Override public void onButtonEvent(Button button, boolean pressed) {
            System.out.println("MockAbcButtonSupplier.onButtonEvent");
        }

        @Override public void close() throws Exception {
            System.out.println("MockAbcButtonSupplier.close");
        }
    }

    public static class RealAbcButtonSupplier implements AbcButtonSupplier {

        private Button buttonA;
        private Button buttonB;
        private Button buttonC;

        private AbcButton.Listener listener;

        public RealAbcButtonSupplier() {
            init();
        }

        private void init() {
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
        public void setListener(AbcButton.Listener listener) {
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
}
