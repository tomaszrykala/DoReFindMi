package com.tomaszrykala.dorefindmi.things.supplier.ledstrip

import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier

const val TURN_OFF_COLOR = -1

interface LedStripSupplier : BaseSupplier {

    fun light(colors: IntArray)

    fun getLength(): Int
}
