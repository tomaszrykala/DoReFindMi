package com.tomaszrykala.dorefindmi.things.supplier.abcbuttons

import com.google.android.things.contrib.driver.button.Button
import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.things.supplier.BaseSupplier

interface AbcButtonsSupplier : BaseSupplier, Button.OnButtonEventListener {

    fun setListener(listener: Listener)

    interface Listener {
        fun onButtonEvent(abcButton: AbcButton, pressed: Boolean)
    }
}
