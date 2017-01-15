package com.tomaszrykala.dorefindmi.things.supplier.abcleds;

public class MockAbcLedsSupplier implements AbcLedsSupplier {

    @Override public void init() {
        System.out.println("MockAbcLedsSupplier.init");
    }

    @Override public void close() throws Exception {
        System.out.println("MockAbcLedsSupplier.close");
    }

    @Override public void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        System.out.print("MockAbcLedsSupplier.lightFor; ");
        System.out.println("onPadA = [" + onPadA + "], onPadB = [" + onPadB + "], onPadC = [" + onPadC + "]");
    }
}
