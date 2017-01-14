package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.Led;
import com.tomaszrykala.dorefindmi.things.suppliers.ledstrip.LedStripSupplier;

public class LedStrip implements AutoCloseable {

    private final LedStripSupplier ledStripSupplier;

    public LedStrip(LedStripSupplier ledStripSupplier) {
        this.ledStripSupplier = ledStripSupplier;
    }

    public boolean isLitAt(Led led) {
        return ledStripSupplier.isLitAt(led);
    }

    public void light(Led led) {
        ledStripSupplier.light(led);
    }

    public void reset() {
        ledStripSupplier.reset();
    }

    @Override public void close() throws Exception {
        ledStripSupplier.close();
    }
}
