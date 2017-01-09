package com.tomaszrykala.dorefindmi.game;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Generator;
import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static com.tomaszrykala.dorefindmi.model.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.model.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.model.Note.FA;
import static com.tomaszrykala.dorefindmi.model.Note.LA;
import static com.tomaszrykala.dorefindmi.model.Note.MI;
import static com.tomaszrykala.dorefindmi.model.Note.RE;
import static com.tomaszrykala.dorefindmi.model.Note.SI;
import static com.tomaszrykala.dorefindmi.model.Note.SO;
import static com.tomaszrykala.dorefindmi.model.Pad.A;
import static com.tomaszrykala.dorefindmi.model.Pad.B;
import static com.tomaszrykala.dorefindmi.model.Pad.C;

public class StepsGenerator {

    private final List<Note> notes = new ArrayList<>(Arrays.asList(
            new Note[]{DO_LO, RE, MI, FA, SO, LA, SI, DO_HI}));
    private final Pad[] pads = new Pad[]{A, B, C};
    private final Generator generator;

    public StepsGenerator(Generator generator) {
        this.generator = generator;
    }

    public List<Step> steps() {
        return generator.getSteps(notes, pads);
    }

    public static class RealGenerator implements Generator {

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

    public static class MockGenerator implements Generator {

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
