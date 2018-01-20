package com.tomaszrykala.dorefindmi.things.controller.ledstrip

import android.support.annotation.VisibleForTesting
import com.tomaszrykala.dorefindmi.domain.Led
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplier
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.TURN_OFF_COLOR
import java.util.*

class LedStripController(private val supplier: LedStripSupplier) : BaseController {

    private val leds = Led.values().filterNot { it == Led.NONE || it == Led.ALL }
    private val ledStates = HashMap<Led, Boolean>(supplier.getLength())

    fun light(led: Led) {
        when (led) {
            Led.ALL -> putAll(true)
            else -> ledStates.put(led, true)
        }
        light()
    }

    fun reset() {
        putAll(false)
        light()
    }

    private fun light() {
        val colors = IntArray(ledStates.size)
        for (led in ledStates.keys) {
            colors[led.index] = if (ledStates[led] as Boolean) led.color else TURN_OFF_COLOR
        }
        supplier.light(colors)
    }

    private fun putAll(all: Boolean) {
        leds.forEach { ledStates.put(it, all) }
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.apply {
            reset()
            close()
        }
    }

    @VisibleForTesting
    fun isLitAt(led: Led): Boolean {
        return if (led == Led.ALL) {
            ledStates.filterValues { !it }.isEmpty()
        } else {
            ledStates[led] as Boolean
        }
    }
}
