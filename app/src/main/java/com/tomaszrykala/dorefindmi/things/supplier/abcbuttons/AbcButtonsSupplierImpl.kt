package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons

import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.tomaszrykala.dorefindmi.domain.AbcButton

class AbcButtonsSupplierImpl : AbcButtonsSupplier {

    private val buttonA: Button by lazy { RainbowHat.openButtonA() }
    private val buttonB: Button by lazy { RainbowHat.openButtonB() }
    private val buttonC: Button by lazy { RainbowHat.openButtonC() }

    private lateinit var listener: AbcButtonsSupplier.Listener

    override fun init() {
        buttonA.setOnButtonEventListener(this)
        buttonB.setOnButtonEventListener(this)
        buttonC.setOnButtonEventListener(this)
    }

    override fun setListener(listener: AbcButtonsSupplier.Listener) {
        this.listener = listener
    }

    override fun onButtonEvent(button: Button, pressed: Boolean) {
        when (button) {
            buttonA -> listener.onButtonEvent(AbcButton.A, pressed)
            buttonB -> listener.onButtonEvent(AbcButton.B, pressed)
            buttonC -> listener.onButtonEvent(AbcButton.C, pressed)
        }
    }

    @Throws(Exception::class)
    override fun close() {
        buttonA.close()
        buttonB.close()
        buttonC.close()
    }
}
