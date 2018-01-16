package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons

import com.google.android.things.contrib.driver.button.Button

class MockAbcButtonsSupplier : AbcButtonsSupplier {

    override fun setListener(listener: AbcButtonsSupplier.Listener) {
        println("MockAbcButtonsSupplier.setListener")
    }

    override fun onButtonEvent(button: Button, pressed: Boolean) {
        print("MockAbcButtonsSupplier.onButtonEvent; ")
        println("button = [$button], pressed = [$pressed]")
    }

    @Throws(Exception::class)
    override fun close() {
        println("MockAbcButtonsSupplier.close")
    }
}
