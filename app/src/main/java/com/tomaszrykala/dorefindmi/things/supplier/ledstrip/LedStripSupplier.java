package com.tomaszrykala.dorefindmi.things.supplier.ledstrip;

import com.tomaszrykala.dorefindmi.model.Led;
import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier;

public interface LedStripSupplier extends BaseSupplier {

    boolean isLitAt(Led led);

    void light(Led led);

    void reset();
}
