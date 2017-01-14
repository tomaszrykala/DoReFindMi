package com.tomaszrykala.dorefindmi.things.supplier.buzzer;

import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier;

public interface BuzzerSupplier extends BaseSupplier {
    void play(double pitch);
}
