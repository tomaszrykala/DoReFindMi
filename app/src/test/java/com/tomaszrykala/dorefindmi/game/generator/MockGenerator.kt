package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.game.Step
import com.tomaszrykala.dorefindmi.model.AbcButton.*
import com.tomaszrykala.dorefindmi.model.Note
import java.util.*

class MockGenerator : Generator {

    override val steps: List<Step>
        get() {
            val steps = arrayOf(
                    Step(A, Note.DO_LO), Step(B, Note.RE), Step(C, Note.MI),
                    Step(A, Note.FA), Step(B, Note.SO), Step(C, Note.LA),
                    Step(A, Note.SI), Step(B, Note.DO_HI))
            return LinkedList(Arrays.asList(*steps))
        }
}
