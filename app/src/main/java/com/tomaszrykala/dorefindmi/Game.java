package com.tomaszrykala.dorefindmi;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

class Game implements PadListener {

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
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    @Override public boolean onPad(Pad pad) {
        final Step step = queue.poll();
        final boolean isGuessed = step.getPad() == pad;
        if (isGuessed) {
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
