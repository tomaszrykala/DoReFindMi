package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

import android.graphics.Color
import com.google.android.things.contrib.driver.apa102.Apa102
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat.LEDSTRIP_LENGTH
import com.tomaszrykala.dorefindmi.domain.Led
import java.io.IOException
import java.util.*

class LedStripSupplierImpl : LedStripSupplier {

    private val ledColorHashMap = HashMap<Led, Boolean>(LEDSTRIP_LENGTH)

    private val apa102: Apa102 by lazy {
        RainbowHat.openLedStrip().apply {
            direction = Apa102.Direction.REVERSED
            brightness = 7
        }
    }

    override fun isLitAt(led: Led): Boolean {
        return ledColorHashMap[led] as Boolean
    }

    override fun light(led: Led) {
        when (led) {
            Led.ALL -> putAll(true)
            else -> ledColorHashMap.put(led, true)
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
            apa102.write(colors)
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
        reset()
        apa102.close()
    }
}
