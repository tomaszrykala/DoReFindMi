package com.tomaszrykala.dorefindmi.things.supplier.digidisplay;

public class MockDigiDisplaySupplier implements DigiDisplaySupplier {

    @Override public void init() {
        System.out.println("MockDigiDisplaySupplier.init");
    }

    @Override public void display(double n) {
        System.out.println("MockDigiDisplaySupplier.display: " + n);
    }

    @Override public void display(String s) {
        System.out.println("MockDigiDisplaySupplier.display: " + s);
    }

    @Override public void close() throws Exception {
        System.out.println("MockDigiDisplaySupplier.close");
    }
}
