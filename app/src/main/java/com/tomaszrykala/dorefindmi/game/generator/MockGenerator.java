package com.tomaszrykala.dorefindmi.game.generator;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MockGenerator implements Generator {

    @NonNull @Override public List<Step> getSteps(List<Note> notes, Pad[] pads) {
        final Step[] steps = {
                new Step(Pad.A, Note.DO_LO),
                new Step(Pad.B, Note.RE),
                new Step(Pad.C, Note.MI),
                new Step(Pad.A, Note.FA),
                new Step(Pad.B, Note.SO),
                new Step(Pad.C, Note.LA),
                new Step(Pad.A, Note.SI),
                new Step(Pad.B, Note.DO_HI),
        };
        final List<Step> stepList = Arrays.asList(steps);
        return new LinkedList<>(stepList);
    }
}
