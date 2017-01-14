package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.Buzzer;
import com.tomaszrykala.dorefindmi.things.LedStrip;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class Game implements PadListener {

    private final LinkedBlockingQueue<Step> queue;
    private final List<Step> steps;

    private final LedStrip ledStrip;
    private final Buzzer buzzer;

    private boolean started;
    private boolean won;


    public Game(List<Step> steps, LedStrip ledStrip, Buzzer buzzer) {
        queue = new LinkedBlockingQueue<>(steps.size());
        this.ledStrip = ledStrip;
        this.buzzer = buzzer;
        this.steps = steps;
        started = true;
        start();
    }

    public synchronized void start() {
        queue.clear();
        queue.addAll(steps);
        ledStrip.reset();
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    @Override public boolean onPad(AbcButton abcButton) {
        final Step step = queue.poll();
        if (step != null && step.getAbcButton() == abcButton) {
            final Note note = step.getNote();
            ledStrip.light(note.led);
            buzzer.buzz(note);
            if (queue.isEmpty()) {
                started = false;
                won = true;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean isWon() {
        return won;
    }
}
