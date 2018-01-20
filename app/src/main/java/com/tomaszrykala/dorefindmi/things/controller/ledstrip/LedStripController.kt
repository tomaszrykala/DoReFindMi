package com.tomaszrykala.dorefindmi.things.controller.ledstrip

import android.support.annotation.VisibleForTesting
import com.tomaszrykala.dorefindmi.domain.Led
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplier
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.TURN_OFF_COLOR
import java.util.*

class LedStripController(private val supplier: LedStripSupplier) : BaseController {

    private val ledColorHashMap = HashMap<Led, Boolean>(supplier.getLength())

    fun light(led: Led) {
        when (led) {
            Led.ALL -> putAll(true)
            else -> ledColorHashMap.put(led, true)
        }
        light()
    }

    fun reset() {
        putAll(false)
        light()
    }

    private fun light() {
        val colors = IntArray(ledColorHashMap.size)
        for (led in ledColorHashMap.keys) {
            colors[led.index] = if (ledColorHashMap[led] as Boolean) led.color else TURN_OFF_COLOR
        }
        supplier.light(colors)
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
        supplier.apply {
            reset()
            close()
        }
    }

    @VisibleForTesting
    fun isLitAt(led: Led): Boolean {
        return if (led == Led.ALL) {
            ledColorHashMap.filterValues { !it }.isEmpty()
        } else {
            ledColorHashMap[led] as Boolean
        }
    }
}
