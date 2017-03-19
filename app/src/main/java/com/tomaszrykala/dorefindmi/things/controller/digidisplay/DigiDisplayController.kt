package com.tomaszrykala.dorefindmi.things.controller.digidisplay

import com.tomaszrykala.dorefindmi.game.Timer
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplier

open class DigiDisplayController(private val supplier: DigiDisplaySupplier) : BaseController, Timer.Listener {
    var isRunning: Boolean = false
        private set
    var counter: Double = 0.toDouble()
        private set

    init {
        supplier.init()
    }

    override fun onTick(tick: Double) {
        counter = tick
        display(counter)
    }

    override fun onStart() {
        isRunning = true
    }

    override fun onStop() {
        isRunning = false
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.close()
    }

    fun display(n: Double) {
        supplier.display(n)
    }

    open fun displayBlocking(text: String, millis: Int) {
        supplier.display(text)
        try {
            Thread.sleep(millis.toLong())
        } catch (ignored: InterruptedException) {
        }
    }
}
