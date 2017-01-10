package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.things.AbcButtonLeds;
import com.tomaszrykala.dorefindmi.things.AbcButtonPads;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;
import com.tomaszrykala.dorefindmi.model.Pad;

public class GameController implements PadListener {

    private final AbcButtonPads abcButtonPads;
    private final AbcButtonLeds abcButtonLeds;
    private final DigitalDisplay digitalDisplay;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtonPads abcButtonPads, AbcButtonLeds abcButtonLeds, DigitalDisplay digitalDisplay, Timer timer, Game game) {
        this.abcButtonPads = abcButtonPads;
        this.abcButtonLeds = abcButtonLeds;
        this.digitalDisplay = digitalDisplay;
        this.timer = timer;
        this.game = game;

        abcButtonPads.setListener(this);
        start();
    }

    @Override public boolean onPad(Pad pad) {
        final boolean onPad = game.onPad(pad);
        abcButtonPads.setLastPressed(pad);
        if (!onPad) {
            restart();
        } else {
            if (game.isWon()) {
                stop();
            } else {
                abcButtonLeds.lightFor(pad);
            }
        }
        return onPad; // TODO: ignored
    }

    private void restart() {
        abcButtonPads.setLastPressed(null);
        abcButtonLeds.reset();
        game.start();
    }

    private void start() {
        abcButtonPads.enable();
        timer.start();
        restart();
    }

    private void stop() {
        timer.stop();
        abcButtonPads.disable();
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public boolean isWon() {
        return game.isWon();
    }
}
