package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController;
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {

    private final Queue<Step> queue;
    private final List<Step> steps;

    private final LedStripController ledStripController;
    private final BuzzerController buzzerController;

    private boolean started;
    private boolean won;

    public Game(List<Step> steps, LedStripController ledStripController, BuzzerController buzzerController) {
        queue = new LinkedBlockingQueue<>(steps.size());
        this.ledStripController = ledStripController;
        this.buzzerController = buzzerController;
        this.steps = steps;
    }

    public void start() {
        queue.clear();
        queue.addAll(steps);
        ledStripController.reset();
        started = true;
        won = false;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean onPad(AbcButton abcButton) {
        final Step step = queue.poll();
        if (step != null) {
            final Note note = step.getNote();
            if (notGuessed(abcButton, step)) {
                buzzerController.buzz(Note.MISS);
                return false;
            } else {
                ledStripController.light(note.led);
                buzzerController.buzz(note);
                if (queue.isEmpty()) {
                    started = false;
                    won = true;
                }
                return true;
            }
        } else {
            return false;
        }
    }

    private boolean notGuessed(AbcButton abcButton, Step step) {
        return step.getAbcButton() != abcButton;
    }

    public boolean isWon() {
        return won;
    }

    void onDestroy() {
        try {
            ledStripController.close();
            buzzerController.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
