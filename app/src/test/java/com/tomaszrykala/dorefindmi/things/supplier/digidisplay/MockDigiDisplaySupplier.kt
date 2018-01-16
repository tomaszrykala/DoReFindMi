package com.tomaszrykala.dorefindmi.things.supplier.digidisplay

class MockDigiDisplaySupplier : DigiDisplaySupplier {

    override fun display(n: Double) {
        println("MockDigiDisplaySupplier.display: " + n)
    }

    override fun display(s: String) {
        println("MockDigiDisplaySupplier.display: " + s)
    }

    @Throws(Exception::class)
    override fun close() {
        println("MockDigiDisplaySupplier.close")
    }
}
