package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.model.AbcButton.*
import com.tomaszrykala.dorefindmi.model.Note
import com.tomaszrykala.dorefindmi.model.Note.*
import java.util.*

abstract class BaseGenerator : Generator {

    val notes: List<Note>
        get() = ArrayList(Arrays.asList(*arrayOf(DO_LO, RE, MI, FA, SO, LA, SI, DO_HI)))

    companion object {

        val BUTTONS = arrayOf(A, B, C)
    }
}
