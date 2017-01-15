package com.tomaszrykala.dorefindmi.game.generator;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MockGenerator extends BaseGenerator {

    @NonNull @Override public List<Step> getSteps() {
        final Step[] steps = {
                new Step(AbcButton.A, Note.DO_LO),
                new Step(AbcButton.B, Note.RE),
                new Step(AbcButton.C, Note.MI),
                new Step(AbcButton.A, Note.FA),
                new Step(AbcButton.B, Note.SO),
                new Step(AbcButton.C, Note.LA),
                new Step(AbcButton.A, Note.SI),
                new Step(AbcButton.B, Note.DO_HI),
        };
        return new LinkedList<>(Arrays.asList(steps));
    }
}
