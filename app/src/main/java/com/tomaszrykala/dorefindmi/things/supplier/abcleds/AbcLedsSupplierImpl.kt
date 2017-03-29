package com.tomaszrykala.dorefindmi.things.supplier.abcleds

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.pio.Gpio

import java.io.IOException

class AbcLedsSupplierImpl : AbcLedsSupplier {

    private var ledA: Gpio? = null
    private var ledB: Gpio? = null
    private var ledC: Gpio? = null

    override fun init() {
        try {
            ledA = RainbowHat.openLed(RainbowHat.LED_RED)
            ledB = RainbowHat.openLed(RainbowHat.LED_GREEN)
            ledC = RainbowHat.openLed(RainbowHat.LED_BLUE)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun lightFor(onPadA: Boolean, onPadB: Boolean, onPadC: Boolean) {
        try {
            ledA!!.value = onPadA
            ledB!!.value = onPadB
            ledC!!.value = onPadC
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    override fun close() {
        ledA!!.close()
        ledB!!.close()
        ledC!!.close()
    }
}
