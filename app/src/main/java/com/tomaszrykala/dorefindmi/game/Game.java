package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.game.generator.Generator;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController;
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {

    private final Queue<Step> stepQueue = new LinkedBlockingQueue<>();
    private final LedStripController ledStripController;
    private final BuzzerController buzzerController;
    private final Generator generator;

    private List<Step> steps;
    private boolean started;
    private boolean won;

    public Game(LedStripController ledStripController, BuzzerController buzzerController, Generator generator) {
        this.ledStripController = ledStripController;
        this.buzzerController = buzzerController;
        this.generator = generator;
        setRoundSteps();
    }

    private void setRoundSteps() {
        steps = generator.getSteps();
    }

    public void start() {
        stepQueue.clear();
        stepQueue.addAll(steps);
        ledStripController.reset();
        started = true;
        won = false;
    }

    void reset() {
        setRoundSteps();
    }

    public boolean isStarted() {
        return started;
    }

    public boolean onPad(AbcButton abcButton) {
        final Step step = stepQueue.poll();
        if (step != null) {
            final Note note = step.getNote();
            if (notGuessed(abcButton, step)) {
                buzzerController.buzz(Note.MISS);
                return false;
            } else {
                ledStripController.light(note.led);
                buzzerController.buzz(note);
                if (stepQueue.isEmpty()) {
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
