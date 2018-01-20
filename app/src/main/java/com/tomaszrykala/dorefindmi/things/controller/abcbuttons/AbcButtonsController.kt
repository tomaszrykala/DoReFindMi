package com.tomaszrykala.dorefindmi.things.controller.abcbuttons

import android.support.annotation.VisibleForTesting
import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.AbcButtonsSupplier

class AbcButtonsController(private val supplier: AbcButtonsSupplier) : BaseController, AbcButtonsSupplier.Listener {

    private lateinit var listener: AbcButton.Listener
    private var lastPressed: AbcButton? = null

    init {
        supplier.setListener(this)
    }

    var isEnabled: Boolean = false
        private set

    fun setListener(listener: AbcButton.Listener) {
        this.listener = listener
    }

    fun enable() {
        isEnabled = true
    }

    fun disable() {
        isEnabled = false
    }

    fun setLastPressed(abcButton: AbcButton?) {
        lastPressed = abcButton
    }

    override fun onButtonEvent(abcButton: AbcButton, pressed: Boolean) {
        if (pressed) {
            listener.onAbcButton(abcButton)
        }
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.close()
    }

    @VisibleForTesting
    fun isLastPressed(abcButton: AbcButton): Boolean = lastPressed === abcButton

    @VisibleForTesting
    fun hasLastPressed(): Boolean = lastPressed != null
}
