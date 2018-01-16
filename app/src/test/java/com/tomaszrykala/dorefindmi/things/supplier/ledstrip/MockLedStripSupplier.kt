package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

import com.tomaszrykala.dorefindmi.domain.Led

class MockLedStripSupplier : LedStripSupplier {

    override fun init() {
        println("MockLedStripSupplier.init")
    }

    override fun isLitAt(led: Led): Boolean {
        println("MockLedStripSupplier.isLitAt")
        return true
    }

    override fun light(led: Led) {
        println("MockLedStripSupplier.light")
    }

    override fun reset() {
        println("MockLedStripSupplier.reset")
    }

    @Throws(Exception::class)
    override fun close() {
        println("MockLedStripSupplier.close")
    }
}
