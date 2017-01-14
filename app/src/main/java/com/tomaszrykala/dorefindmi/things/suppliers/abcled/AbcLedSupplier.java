package com.tomaszrykala.dorefindmi.things.suppliers.abcled;

import com.tomaszrykala.dorefindmi.model.AbcLed;
import com.tomaszrykala.dorefindmi.things.suppliers.BaseSupplier;

public interface AbcLedSupplier extends BaseSupplier {

    void lightFor(boolean onPadA, boolean onPadB, boolean onPadC);

    boolean isLitAt(AbcLed abcLed);
}
