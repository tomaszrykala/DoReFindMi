package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.game.Step
import com.tomaszrykala.dorefindmi.model.AbcButton
import com.tomaszrykala.dorefindmi.model.Note
import java.util.*

class GeneratorImpl : Generator {

    private val BUTTONS = arrayOf(AbcButton.A, AbcButton.B, AbcButton.C)

    private val notes: List<Note>
        get() = ArrayList(Arrays.asList(Note.DO_LO, Note.RE, Note.MI, Note.FA, Note.SO, Note.LA, Note.SI, Note.DO_HI))

    override val steps: List<Step>
        get() {
            val steps = LinkedList<Step>()
            val notes = notes as MutableList
            val random = Random()
            while (!notes.isEmpty()) {
                val nextInt = random.nextInt(BUTTONS.size)
                val abcButton = BUTTONS[nextInt]
                val note = notes.removeAt(random.nextInt(notes.size))
                steps.add(Step(abcButton, note))
            }

            Collections.sort(steps) { o1, o2 -> o1.note.order - o2.note.order }
            return steps
        }
}
