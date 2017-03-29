package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

import com.tomaszrykala.dorefindmi.model.Led
import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier

interface LedStripSupplier : BaseSupplier {

    fun isLitAt(led: Led): Boolean

    fun light(led: Led)

    fun reset()
}
