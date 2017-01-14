package com.tomaszrykala.dorefindmi.things.suppliers.abcbutton;

import com.google.android.things.contrib.driver.button.Button;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.suppliers.BaseSupplier;

public interface AbcButtonSupplier extends BaseSupplier, Button.OnButtonEventListener {
    void setListener(AbcButton.Listener listener);
}
