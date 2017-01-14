package com.tomaszrykala.dorefindmi.things.suppliers.buzzer;

public interface BuzzerSupplier extends AutoCloseable {
    void play(double pitch);
}
