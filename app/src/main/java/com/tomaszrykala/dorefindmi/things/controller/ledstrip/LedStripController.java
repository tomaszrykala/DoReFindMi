package com.tomaszrykala.dorefindmi.things.controller.ledstrip;

import com.tomaszrykala.dorefindmi.model.Led;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplier;

public class LedStripController implements BaseController {

    private final LedStripSupplier supplier;

    public LedStripController(LedStripSupplier ledStripSupplier) {
        supplier = ledStripSupplier;
        supplier.init();
    }

    public boolean isLitAt(Led led) {
        return supplier.isLitAt(led);
    }

    public void light(Led led) {
        supplier.light(led);
    }

    public void reset() {
        supplier.reset();
    }

    @Override public void close() throws Exception {
        supplier.close();
    }
}
