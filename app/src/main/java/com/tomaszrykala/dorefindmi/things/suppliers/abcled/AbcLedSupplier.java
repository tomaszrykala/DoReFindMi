package com.tomaszrykala.dorefindmi.things.suppliers.abcled;

public interface AbcLedSupplier extends AutoCloseable {

    void lightFor(boolean onPadA, boolean onPadB, boolean onPadC);
}
