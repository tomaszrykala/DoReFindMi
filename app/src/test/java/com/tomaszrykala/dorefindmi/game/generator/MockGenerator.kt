package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.game.Step
import com.tomaszrykala.dorefindmi.model.AbcButton
import com.tomaszrykala.dorefindmi.model.Note
import java.util.*

class MockGenerator : BaseGenerator() {

    override val steps: List<Step>
        get() {
            val steps = arrayOf(
                    Step(AbcButton.A, Note.DO_LO), Step(AbcButton.B, Note.RE), Step(AbcButton.C, Note.MI),
                    Step(AbcButton.A, Note.FA), Step(AbcButton.B, Note.SO), Step(AbcButton.C, Note.LA),
                    Step(AbcButton.A, Note.SI), Step(AbcButton.B, Note.DO_HI))
            return LinkedList(Arrays.asList(*steps))
        }
}
