package com.tomaszrykala.dorefindmi.things.supplier.abcleds;

import com.tomaszrykala.dorefindmi.model.AbcLed;
import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier;

public interface AbcLedsSupplier extends BaseSupplier {

    void lightFor(boolean onPadA, boolean onPadB, boolean onPadC);

    boolean isLitAt(AbcLed abcLed);
}
