package com.tomaszrykala.dorefindmi.things.supplier.abcleds

import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier

interface AbcLedsSupplier : BaseSupplier {

    fun lightFor(onPadA: Boolean, onPadB: Boolean, onPadC: Boolean)
}
