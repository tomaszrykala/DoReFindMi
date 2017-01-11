package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.AbcButtons;
import com.tomaszrykala.dorefindmi.things.AbcLeds;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;

public class GameController implements PadListener {

    private final AbcButtons abcButtons;
    private final AbcLeds abcLeds;
    private final DigitalDisplay digitalDisplay;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtons abcButtons, AbcLeds abcLeds, DigitalDisplay digitalDisplay, Timer timer, Game game) {
        this.abcButtons = abcButtons;
        this.abcLeds = abcLeds;
        this.digitalDisplay = digitalDisplay;
        this.timer = timer;
        this.game = game;

        this.abcButtons.setListener(this);
        start();
    }

    @Override public boolean onPad(AbcButton abcButton) {
        final boolean onPad = game.onPad(abcButton);
        abcButtons.setLastPressed(abcButton);
        if (!onPad) {
            restart();
        } else {
            if (game.isWon()) {
                stop();
            } else {
                abcLeds.lightFor(abcButton);
            }
        }
        return onPad; // TODO: ignored
    }

    private void restart() {
        abcButtons.setLastPressed(null);
        abcLeds.reset();
        game.start();
    }

    private void start() {
        abcButtons.enable();
        timer.start();
        restart();
    }

    private void stop() {
        timer.stop();
        abcButtons.disable();
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public boolean isWon() {
        return game.isWon();
    }
}
