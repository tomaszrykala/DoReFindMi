package com.tomaszrykala.dorefindmi.things.suppliers.ledstrip;

import com.tomaszrykala.dorefindmi.model.Led;
import com.tomaszrykala.dorefindmi.things.suppliers.BaseSupplier;

public interface LedStripSupplier extends BaseSupplier {

    boolean isLitAt(Led led);

    void light(Led led);

    void reset();
}
