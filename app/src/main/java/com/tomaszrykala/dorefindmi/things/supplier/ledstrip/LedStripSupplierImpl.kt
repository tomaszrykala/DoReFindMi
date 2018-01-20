package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

import android.graphics.Color
import com.google.android.things.contrib.driver.apa102.Apa102
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat
import java.io.IOException

class LedStripSupplierImpl : LedStripSupplier {

    private val apa102: Apa102 by lazy {
        RainbowHat.openLedStrip().apply {
            direction = Apa102.Direction.REVERSED
            brightness = 7
        }
    }

    override fun getLength(): Int = RainbowHat.LEDSTRIP_LENGTH

    override fun light(colors: IntArray) {
        val writeColors = IntArray(colors.size)
        colors.forEachIndexed { index, color ->
            if (color == TURN_OFF_COLOR) {
                writeColors[index] = Color.BLACK
            } else {
                writeColors[index] = color
            }
        }

        try {
            apa102.write(writeColors)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    override fun close() {
        apa102.close()
    }
}
