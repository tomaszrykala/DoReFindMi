package com.tomaszrykala.dorefindmi.things.suppliers.abcled;

public class MockAbcLedSupplier implements AbcLedSupplier {

    @Override public void close() throws Exception {
        System.out.println("MockAbcLedSupplier.close");
    }

    @Override public void lightFor(boolean onPadA, boolean onPadB, boolean onPadC) {
        System.out.print("MockAbcLedSupplier.lightFor; ");
        System.out.println("onPadA = [" + onPadA + "], onPadB = [" + onPadB + "], onPadC = [" + onPadC + "]");
    }
}
