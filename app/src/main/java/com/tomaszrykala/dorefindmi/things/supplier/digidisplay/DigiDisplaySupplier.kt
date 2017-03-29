package com.tomaszrykala.dorefindmi.things.supplier.digidisplay

import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier

interface DigiDisplaySupplier : BaseSupplier {

    fun display(n: Double)

    fun display(s: String)
}
