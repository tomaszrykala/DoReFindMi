package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons;

import com.google.android.things.contrib.driver.button.Button;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier;

public interface AbcButtonsSupplier extends BaseSupplier, Button.OnButtonEventListener {
    void setListener(Listener listener);

    interface Listener {
        void onButtonEvent(AbcButton abcButton, boolean pressed);
    }
}
