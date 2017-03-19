package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons

import com.google.android.things.contrib.driver.button.Button
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.tomaszrykala.dorefindmi.model.AbcButton

import java.io.IOException

class AbcButtonsSupplierImpl : AbcButtonsSupplier {

    private var buttonA: Button? = null
    private var buttonB: Button? = null
    private var buttonC: Button? = null

    private var listener: AbcButtonsSupplier.Listener? = null

    override fun init() {
        try {
            buttonA = RainbowHat.openButton(RainbowHat.BUTTON_A)
            buttonB = RainbowHat.openButton(RainbowHat.BUTTON_B)
            buttonC = RainbowHat.openButton(RainbowHat.BUTTON_C)

            buttonA!!.setOnButtonEventListener(this)
            buttonB!!.setOnButtonEventListener(this)
            buttonC!!.setOnButtonEventListener(this)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun setListener(listener: AbcButtonsSupplier.Listener) {
        this.listener = listener
    }

    override fun onButtonEvent(button: Button, pressed: Boolean) {
        if (button === buttonA) {
            listener!!.onButtonEvent(AbcButton.A, pressed)
        } else if (button === buttonB) {
            listener!!.onButtonEvent(AbcButton.B, pressed)
        } else if (button === buttonC) {
            listener!!.onButtonEvent(AbcButton.C, pressed)
        }
    }

    @Throws(Exception::class)
    override fun close() {
        buttonA!!.close()
        buttonB!!.close()
        buttonC!!.close()
    }
}
