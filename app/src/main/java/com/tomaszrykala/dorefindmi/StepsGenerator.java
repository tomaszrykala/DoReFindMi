package com.tomaszrykala.dorefindmi;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.tomaszrykala.dorefindmi.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.Note.FA;
import static com.tomaszrykala.dorefindmi.Note.LA;
import static com.tomaszrykala.dorefindmi.Note.MI;
import static com.tomaszrykala.dorefindmi.Note.RE;
import static com.tomaszrykala.dorefindmi.Note.SI;
import static com.tomaszrykala.dorefindmi.Note.SO;
import static com.tomaszrykala.dorefindmi.Pad.A;
import static com.tomaszrykala.dorefindmi.Pad.B;
import static com.tomaszrykala.dorefindmi.Pad.C;

class StepsGenerator {

    private final List<Note> notes = new ArrayList<>(Arrays.asList(
            new Note[]{DO_LO, RE, MI, FA, SO, LA, SI, DO_HI}));
    private final Pad[] pads = new Pad[]{A, B, C};
    private final Generator generator;

    StepsGenerator(Generator generator) {
        this.generator = generator;
    }

    List<Step> steps() {
        return generator.getSteps(notes, pads);
    }

    static class RealGenerator implements Generator {

        @NonNull @Override public List<Step> getSteps(List<Note> notes, Pad[] pads) {
            final List<Step> steps = new LinkedList<>();
            while (!notes.isEmpty()) {
                final Pad pad = pads[new Random().nextInt(pads.length)];
                final Note note = notes.remove(new Random().nextInt(notes.size()));
                steps.add(new Step(pad, note));
            }

            Collections.sort(steps, new Comparator<Step>() {
                @Override public int compare(Step o1, Step o2) {
                    return o1.getNote().order - o2.getNote().order;
                }
            });
            return steps;
        }
    }

    static class MockGenerator implements Generator {

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
}
