package com.tomaszrykala.dorefindmi.game.generator;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RealGenerator extends BaseGenerator {

    @NonNull @Override public List<Step> getSteps() {
        final List<Step> steps = new LinkedList<>();
        final List<Note> notes = getNotes();
        final Random random = new Random();
        while (!notes.isEmpty()) {
            final AbcButton abcButton = BUTTONS[random.nextInt(BUTTONS.length)];
            final Note note = notes.remove(random.nextInt(notes.size()));
            steps.add(new Step(abcButton, note));
        }

        Collections.sort(steps, new Comparator<Step>() {
            @Override public int compare(Step o1, Step o2) {
                return o1.getNote().order - o2.getNote().order;
            }
        });
        return steps;
    }
}
