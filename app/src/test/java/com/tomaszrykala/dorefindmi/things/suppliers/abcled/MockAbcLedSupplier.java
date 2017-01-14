package com.tomaszrykala.dorefindmi.things.suppliers.abcled;

import com.tomaszrykala.dorefindmi.model.AbcLed;

public class MockAbcLedSupplier implements AbcLedSupplier {

    @Override public void init() {
        System.out.println("MockAbcLedSupplier.init");
    }

    @Override public void close() throws Exception {
        System.out.println("MockAbcLedSupplier.close");
    }

    @Override public void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        System.out.print("MockAbcLedSupplier.lightFor; ");
        System.out.println("onPadA = [" + onPadA + "], onPadB = [" + onPadB + "], onPadC = [" + onPadC + "]");
    }

    @Override public boolean isLitAt(AbcLed abcLed) {
        System.out.println("MockAbcLedSupplier.isLitAt");
        return false;
    }
}
