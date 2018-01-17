package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.domain.Step
import com.tomaszrykala.dorefindmi.domain.AbcButton.*
import com.tomaszrykala.dorefindmi.domain.Note

class MockGenerator : Generator {

    override val steps: List<Step>
        get() = arrayOf(
                Step(A, Note.DO_LO), Step(B, Note.RE), Step(C, Note.MI),
                Step(A, Note.FA), Step(B, Note.SO), Step(C, Note.LA),
                Step(A, Note.SI), Step(B, Note.DO_HI)).asList()
}
