package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.things.AbcButtonsPad;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;
import com.tomaszrykala.dorefindmi.model.Pad;

public class GameController implements PadListener {

    private final AbcButtonsPad abcButtonsPad;
    private final DigitalDisplay digitalDisplay;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtonsPad abcButtonsPad, DigitalDisplay digitalDisplay, Timer timer, Game game) {
        this.abcButtonsPad = abcButtonsPad;
        this.digitalDisplay = digitalDisplay;
        this.timer = timer;
        this.game = game;
        start();
    }

    @Override public boolean onPad(Pad pad) {
        final boolean onPad = game.onPad(pad);
        abcButtonsPad.setLastPressed(pad);
        if (!onPad) {
            restart();
        } else {
            if (game.isWon()) {
                stop();
            }
        }
        return onPad; // TODO: ignored
    }

    private void restart() {
        abcButtonsPad.setLastPressed(null);
        game.start();
    }

    private void start() {
        abcButtonsPad.enable();
        timer.start();
        restart();
    }

    private void stop() {
        timer.stop();
        abcButtonsPad.disable();
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public boolean isWon() {
        return game.isWon();
    }
}
