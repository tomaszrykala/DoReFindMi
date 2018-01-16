package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons

import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.tomaszrykala.dorefindmi.domain.AbcButton

class AbcButtonsSupplierImpl : AbcButtonsSupplier {

    private val buttons: Map<Button, AbcButton> = mapOf(
            RainbowHat.openButtonA() to AbcButton.A,
            RainbowHat.openButtonB() to AbcButton.B,
            RainbowHat.openButtonC() to AbcButton.C
    )

    private lateinit var listener: AbcButtonsSupplier.Listener

    override fun init() {
        buttons.keys.forEach { it.setOnButtonEventListener(this) }
    }

    override fun setListener(listener: AbcButtonsSupplier.Listener) {
        this.listener = listener
    }

    override fun onButtonEvent(button: Button, pressed: Boolean) {
        buttons[button]?.let { listener.onButtonEvent(it, pressed) }
    }

    @Throws(Exception::class)
    override fun close() {
        buttons.keys.forEach { it.close() }
    }
}
