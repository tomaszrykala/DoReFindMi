package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons;

import com.google.android.things.contrib.driver.button.Button;

public class MockAbcButtonsSupplier implements AbcButtonsSupplier {

    @Override public void init() {
        System.out.println("MockAbcButtonsSupplier.init");
    }

    @Override public void setListener(Listener listener) {
        System.out.println("MockAbcButtonsSupplier.setListener");
    }

    @Override public void onButtonEvent(Button button, boolean pressed) {
        System.out.print("MockAbcButtonsSupplier.onButtonEvent; ");
        System.out.println("button = [" + button + "], pressed = [" + pressed + "]");
    }

    @Override public void close() throws Exception {
        System.out.println("MockAbcButtonsSupplier.close");
    }
}
