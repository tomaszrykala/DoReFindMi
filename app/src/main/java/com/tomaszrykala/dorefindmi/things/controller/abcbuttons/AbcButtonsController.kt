package com.tomaszrykala.dorefindmi.things.controller.abcbuttons

import com.tomaszrykala.dorefindmi.model.AbcButton
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.AbcButtonsSupplier

class AbcButtonsController(private val supplier: AbcButtonsSupplier) : BaseController, AbcButtonsSupplier.Listener {
    private var listener: AbcButton.Listener? = null

    var isEnabled: Boolean = false
        private set
    private var lastPressed: AbcButton? = null

    fun setListener(listener: AbcButton.Listener) {
        this.listener = listener
    }

    fun enable() {
        isEnabled = true
    }

    fun disable() {
        isEnabled = false
    }

    fun isLastPressed(abcButton: AbcButton): Boolean = lastPressed === abcButton

    fun setLastPressed(abcButton: AbcButton?) {
        lastPressed = abcButton
    }

    fun hasLastPressed(): Boolean = lastPressed != null

    init {
        supplier.setListener(this)
        supplier.init()
    }

    override fun onButtonEvent(abcButton: AbcButton, pressed: Boolean) {
        if (pressed) {
            when (abcButton) {
                AbcButton.A -> listener!!.onAbcButton(AbcButton.A)
                AbcButton.B -> listener!!.onAbcButton(AbcButton.B)
                AbcButton.C -> listener!!.onAbcButton(AbcButton.C)
            }
        }
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.close()
    }
}
