package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.domain.Step
import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.domain.Note
import java.util.*

class GeneratorImpl : Generator {

    private val buttons = arrayOf(AbcButton.A, AbcButton.B, AbcButton.C)

    private val notes: List<Note>
        get() = arrayOf(Note.DO_LO, Note.RE, Note.MI, Note.FA, Note.SO, Note.LA, Note.SI, Note.DO_HI).asList()

    override val steps: List<Step>
        get() {
            val steps = LinkedList<Step>()
            val notes = notes as MutableList
            val random = Random()
            while (!notes.isEmpty()) {
                val nextInt = random.nextInt(buttons.size)
                val abcButton = buttons[nextInt]
                val note = notes.removeAt(random.nextInt(notes.size))
                steps.add(Step(abcButton, note))
            }

            Collections.sort(steps) { o1, o2 -> o1.note.order - o2.note.order }
            return steps
        }
}
