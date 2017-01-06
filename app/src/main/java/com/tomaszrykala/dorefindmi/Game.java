package com.tomaszrykala.dorefindmi;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

class Game {

    private final List<Step> steps;
    private boolean started;
    private boolean won;

    private final LinkedBlockingQueue<Step> queue;

    public Game(List<Step> steps) {
        queue = new LinkedBlockingQueue<>(steps.size());
        this.steps = steps;
        started = true;
        start();
    }

    private synchronized void start() {
        queue.clear();
        queue.addAll(steps);
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean onPad(Pad pad) {
        final boolean isGuessed = queue.poll().getPad() == pad;
        if (queue.isEmpty()) {
            started = false;
            won = true;
            return true;
        } else {
            if (isGuessed) {
                return true;
            } else {
                start();
                return false;
            }
        }
    }

    public boolean isWon() {
        return won;
    }
}
