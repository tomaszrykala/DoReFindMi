package com.tomaszrykala.dorefindmi.things.controller.ledstrip;

import com.tomaszrykala.dorefindmi.model.Led;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplier;

public class LedStripController implements BaseController {

    private final LedStripSupplier ledStripSupplier;

    public LedStripController(LedStripSupplier ledStripSupplier) {
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
