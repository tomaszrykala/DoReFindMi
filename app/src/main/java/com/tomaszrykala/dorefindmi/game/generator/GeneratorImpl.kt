package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.domain.Note
import com.tomaszrykala.dorefindmi.domain.Step
import java.util.*
import kotlin.collections.ArrayList

class GeneratorImpl : Generator {

    private val buttons = arrayOf(AbcButton.A, AbcButton.B, AbcButton.C)

    private val notes: List<Note>
        get() = arrayOf(Note.DO_LO, Note.RE, Note.MI, Note.FA, Note.SO, Note.LA, Note.SI, Note.DO_HI).asList()

    override val steps: List<Step>
        get() {
            val steps = LinkedList<Step>()
            val mutableNotes = ArrayList(notes)
            val random = Random()
            while (!mutableNotes.isEmpty()) {
                val nextButton = random.nextInt(buttons.size)
                val abcButton = buttons[nextButton]
                val nextNote = random.nextInt(mutableNotes.size)
                val note = mutableNotes.removeAt(nextNote)
                steps.add(Step(abcButton, note))
            }

            Collections.sort(steps) { o1, o2 -> o1.note.order - o2.note.order }
            return steps
        }
}
