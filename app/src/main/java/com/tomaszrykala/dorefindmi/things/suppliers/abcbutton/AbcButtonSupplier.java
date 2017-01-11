package com.tomaszrykala.dorefindmi.things.suppliers.abcbutton;

import com.google.android.things.contrib.driver.button.Button;
import com.tomaszrykala.dorefindmi.model.AbcButton;

public interface AbcButtonSupplier extends AutoCloseable, Button.OnButtonEventListener {
    void setListener(AbcButton.Listener listener);
}
