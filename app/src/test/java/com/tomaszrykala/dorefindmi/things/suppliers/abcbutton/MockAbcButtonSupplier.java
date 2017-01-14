package com.tomaszrykala.dorefindmi.things.suppliers.abcbutton;

import com.google.android.things.contrib.driver.button.Button;

public class MockAbcButtonSupplier implements AbcButtonSupplier {

    @Override public void init() {
        System.out.println("MockAbcButtonSupplier.init");
    }

    @Override public void setListener(Listener listener) {
        System.out.println("MockAbcButtonSupplier.setListener");
    }

    @Override public void onButtonEvent(Button button, boolean pressed) {
        System.out.print("MockAbcButtonSupplier.onButtonEvent; ");
        System.out.println("button = [" + button + "], pressed = [" + pressed + "]");
    }

    @Override public void close() throws Exception {
        System.out.println("MockAbcButtonSupplier.close");
    }
}
