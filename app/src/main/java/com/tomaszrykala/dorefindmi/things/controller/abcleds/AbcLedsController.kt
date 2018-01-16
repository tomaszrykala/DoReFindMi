package com.tomaszrykala.dorefindmi.things.controller.abcleds

import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.domain.AbcLed
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.AbcLedsSupplier

class AbcLedsController(private val supplier: AbcLedsSupplier) : BaseController, AbcLed.Listener {

    private val lit = booleanArrayOf(false, false, false)

    fun isLitAt(abcLed: AbcLed): Boolean = when (abcLed) {
        AbcLed.A -> lit[0]
        AbcLed.B -> lit[1]
        AbcLed.C -> lit[2]
    }

    override fun lightFor(abcButton: AbcButton) {
        when (abcButton) {
            AbcButton.A -> setLitLeds(true, false, false)
            AbcButton.B -> setLitLeds(false, true, false)
            AbcButton.C -> setLitLeds(false, false, true)
        }
        lightFor(lit[0], lit[1], lit[2])
    }

    override fun reset() {
        setLitLeds(false, false, false)
        lightFor(lit[0], lit[1], lit[2])
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.close()
    }

    private fun lightFor(onPadA: Boolean, onPadB: Boolean, onPadC: Boolean) {
        supplier.lightFor(onPadA, onPadB, onPadC)
    }

    private fun setLitLeds(zero: Boolean, one: Boolean, two: Boolean) {
        lit[0] = zero
        lit[1] = one
        lit[2] = two
    }
}
