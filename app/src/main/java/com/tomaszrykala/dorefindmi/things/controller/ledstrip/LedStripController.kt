package com.tomaszrykala.dorefindmi.things.controller.ledstrip

import com.tomaszrykala.dorefindmi.domain.Led
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplier

class LedStripController(private val supplier: LedStripSupplier) : BaseController {

    init {
        supplier.init()
    }

    fun isLitAt(led: Led): Boolean = supplier.isLitAt(led)

    fun light(led: Led) {
        supplier.light(led)
    }

    fun reset() {
        supplier.reset()
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.close()
    }
}
