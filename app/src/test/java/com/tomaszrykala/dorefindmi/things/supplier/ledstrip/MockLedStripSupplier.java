package com.tomaszrykala.dorefindmi.things.supplier.ledstrip;

import com.tomaszrykala.dorefindmi.model.Led;

public class MockLedStripSupplier implements LedStripSupplier {

    @Override public void init() {
        System.out.println("MockLedStripSupplier.init");
    }

    @Override public boolean isLitAt(Led led) {
        System.out.println("MockLedStripSupplier.isLitAt");
        return true;
    }

    @Override public void light(Led led) {
        System.out.println("MockLedStripSupplier.light");
    }

    @Override public void reset() {
        System.out.println("MockLedStripSupplier.reset");
    }

    @Override public void close() throws Exception {
        System.out.println("MockLedStripSupplier.close");
    }
}
