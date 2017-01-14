package com.tomaszrykala.dorefindmi.things.suppliers.buzzer;

public class MockBuzzerSupplier implements BuzzerSupplier {

    @Override public void play(double pitch) {
        System.out.print("MockBuzzerSupplier.play; ");
        System.out.println("pitch = [" + pitch + "]");
    }

    @Override public void close() throws Exception {
        System.out.println("MockBuzzerSupplier.close");
    }
}
