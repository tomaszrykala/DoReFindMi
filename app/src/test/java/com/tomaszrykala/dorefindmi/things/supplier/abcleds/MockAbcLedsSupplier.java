package com.tomaszrykala.dorefindmi.things.supplier.abcleds;

import com.tomaszrykala.dorefindmi.model.AbcLed;

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

    @Override public boolean isLitAt(AbcLed abcLed) {
        System.out.println("MockAbcLedsSupplier.isLitAt");
        return true; // TODO
    }
}
