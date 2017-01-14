package com.tomaszrykala.dorefindmi.things.suppliers.buzzer;

import com.tomaszrykala.dorefindmi.things.suppliers.BaseSupplier;

public interface BuzzerSupplier extends BaseSupplier {
    void play(double pitch);
}
