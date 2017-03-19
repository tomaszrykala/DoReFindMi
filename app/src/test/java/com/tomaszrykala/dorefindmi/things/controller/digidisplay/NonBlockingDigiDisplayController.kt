package com.tomaszrykala.dorefindmi.things.controller.digidisplay

import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplier

class NonBlockingDigiDisplayController(digiDisplaySupplier: DigiDisplaySupplier) : DigiDisplayController(digiDisplaySupplier) {

    override fun displayBlocking(text: String, millis: Int) {
        super.displayBlocking(text, 0)
    }
}
