package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.game.Step
import java.util.*

class GeneratorImpl : BaseGenerator() {

    override val steps: List<Step>
        get() {
            val steps = LinkedList<Step>()
            val notes = notes as MutableList
            val random = Random()
            while (!notes.isEmpty()) {
                val nextInt = random.nextInt(BaseGenerator.Companion.BUTTONS.size)
                val abcButton = BaseGenerator.Companion.BUTTONS[nextInt]
                val note = notes.removeAt(random.nextInt(notes.size))
                steps.add(Step(abcButton, note))
            }

            Collections.sort(steps) { o1, o2 -> o1.note.order - o2.note.order }
            return steps
        }
}
