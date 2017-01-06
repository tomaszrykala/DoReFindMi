package com.tomaszrykala.dorefindmi;

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

    private StepsGenerator() {
        // no outside instances
    }

    static List<Step> steps() {
        final List<Step> steps = new LinkedList<>();
        final StepsGenerator generator = new StepsGenerator();
        while (!generator.notes.isEmpty()) {
            final Pad pad = generator.pads[new Random().nextInt(generator.pads.length)];
            final Note note = generator.notes.remove(new Random().nextInt(generator.notes.size()));
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
