package com.tomaszrykala.dorefindmi.things.supplier.abcleds

class MockAbcLedsSupplier : AbcLedsSupplier {

    @Throws(Exception::class)
    override fun close() {
        println("MockAbcLedsSupplier.close")
    }

    override fun lightFor(onPadA: Boolean, onPadB: Boolean, onPadC: Boolean) {
        print("MockAbcLedsSupplier.lightFor; ")
        println("onPadA = [$onPadA], onPadB = [$onPadB], onPadC = [$onPadC]")
    }
}
