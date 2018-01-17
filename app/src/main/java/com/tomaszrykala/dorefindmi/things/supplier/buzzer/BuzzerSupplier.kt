package com.tomaszrykala.dorefindmi.things.supplier.buzzer

import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier

interface BuzzerSupplier : BaseSupplier {

    fun play(pitch: Double)
}
