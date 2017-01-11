package com.tomaszrykala.dorefindmi.things.suppliers.abcbutton;

import com.google.android.things.contrib.driver.button.Button;
import com.tomaszrykala.dorefindmi.model.AbcButton;

public class MockAbcButtonSupplier implements AbcButtonSupplier {

    @Override public void setListener(AbcButton.Listener listener) {
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