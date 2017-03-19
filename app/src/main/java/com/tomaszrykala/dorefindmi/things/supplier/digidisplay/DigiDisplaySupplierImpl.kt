package com.tomaszrykala.dorefindmi.things.supplier.digidisplay

import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay
import com.google.android.things.contrib.driver.ht16k33.Ht16k33
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat

import java.io.IOException

class DigiDisplaySupplierImpl : DigiDisplaySupplier {

    private var alphanumericDisplay: AlphanumericDisplay? = null

    override fun init() {
        try {
            alphanumericDisplay = RainbowHat.openDisplay()
            alphanumericDisplay!!.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX)
            alphanumericDisplay!!.setEnabled(true)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun display(n: Double) {
        try {
            alphanumericDisplay!!.display(n)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun display(s: String) {
        try {
            alphanumericDisplay!!.display(s)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    override fun close() {
        alphanumericDisplay!!.close()
    }
}
