package com.tomaszrykala.dorefindmi.things.controller.buzzer

import com.tomaszrykala.dorefindmi.domain.Note
import com.tomaszrykala.dorefindmi.things.controller.BaseController
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.BuzzerSupplier

class BuzzerController(private val supplier: BuzzerSupplier) : BaseController {

    private var lastBuzzed: Note? = null

    fun lastBuzzedAt(note: Note): Boolean = lastBuzzed === note

    fun buzz(note: Note) {
        supplier.play(note.pitch)
        lastBuzzed = note
    }

    @Throws(Exception::class)
    override fun close() {
        supplier.close()
    }
}
