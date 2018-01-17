package com.tomaszrykala.dorefindmi.things.supplier.buzzer

class MockBuzzerSupplier : BuzzerSupplier {

    override fun play(pitch: Double) {
        print("MockBuzzerSupplier.play; ")
        println("pitch = [$pitch]")
    }

    @Throws(Exception::class)
    override fun close() {
        println("MockBuzzerSupplier.close")
    }
}
