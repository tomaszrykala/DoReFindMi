package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

import android.graphics.Color
import com.google.android.things.contrib.driver.apa102.Apa102
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat.BUS_LEDSTRIP
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat.LEDSTRIP_LENGTH
import com.tomaszrykala.dorefindmi.model.Led
import java.io.IOException
import java.util.*

class LedStripSupplierImpl : LedStripSupplier {

    private val ledColorHashMap = HashMap<Led, Boolean>(LEDSTRIP_LENGTH)
    private var apa102: Apa102? = null

    override fun init() {
        try {
            apa102 = openLedStrip()
            apa102!!.brightness = MAX_BRIGHTNESS
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun isLitAt(led: Led): Boolean {
        return ledColorHashMap[led] as Boolean
    }

    override fun light(led: Led) {
        if (led === Led.ALL) {
            putAll(true)
        } else {
            ledColorHashMap.put(led, true)
        }
        light()
    }

    override fun reset() {
        putAll(false)
        light()
    }

    private fun light() {
        val colors = IntArray(LEDSTRIP_LENGTH)
        for (led in ledColorHashMap.keys) {
            colors[led.index] = if (ledColorHashMap[led] as Boolean) led.color else Color.BLACK
        }

        try {
            apa102!!.write(colors)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun putAll(all: Boolean) {
        ledColorHashMap.put(Led.ZERO, all)
        ledColorHashMap.put(Led.ONE, all)
        ledColorHashMap.put(Led.TWO, all)
        ledColorHashMap.put(Led.THREE, all)
        ledColorHashMap.put(Led.FOUR, all)
        ledColorHashMap.put(Led.FIVE, all)
        ledColorHashMap.put(Led.SIX, all)
    }

    @Throws(Exception::class)
    override fun close() {
        apa102!!.close()
    }

    /**
     * No Direction.REVERSED option in RainbowHAT driver factory.
     */
    @Throws(IOException::class)
    private fun openLedStrip(): Apa102 {
        return Apa102(BUS_LEDSTRIP, Apa102.Mode.BGR, Apa102.Direction.REVERSED)
    }

    companion object {

        private val MAX_BRIGHTNESS = 7 // max = 31
    }
}
