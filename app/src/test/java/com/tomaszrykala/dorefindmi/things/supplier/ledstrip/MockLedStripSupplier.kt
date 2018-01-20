package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

class MockLedStripSupplier : LedStripSupplier {

    override fun light(colors: IntArray) {
        println("MockLedStripSupplier.light")
    }

    override fun getLength(): Int {
        println("MockLedStripSupplier.getLength")
        return 7
    }

    @Throws(Exception::class)
    override fun close() {
        println("MockLedStripSupplier.close")
    }
}
