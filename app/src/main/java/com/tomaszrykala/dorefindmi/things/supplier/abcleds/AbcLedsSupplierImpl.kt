package com.tomaszrykala.dorefindmi.things.supplier.abcleds

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio

import java.io.IOException

class AbcLedsSupplierImpl : AbcLedsSupplier {

    private val ledA: Gpio by lazy { RainbowHat.openLed(RainbowHat.LED_RED) }
    private val ledB: Gpio by lazy { RainbowHat.openLed(RainbowHat.LED_GREEN) }
    private val ledC: Gpio by lazy { RainbowHat.openLed(RainbowHat.LED_BLUE) }

    override fun init() {}

    override fun lightFor(onPadA: Boolean, onPadB: Boolean, onPadC: Boolean) {
        try {
            ledA.value = onPadA
            ledB.value = onPadB
            ledC.value = onPadC
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    override fun close() {
        ledA.close()
        ledB.close()
        ledC.close()
    }
}
