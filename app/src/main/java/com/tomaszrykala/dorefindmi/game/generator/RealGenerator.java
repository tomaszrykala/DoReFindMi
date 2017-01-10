package com.tomaszrykala.dorefindmi.game.generator;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RealGenerator implements Generator {

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
