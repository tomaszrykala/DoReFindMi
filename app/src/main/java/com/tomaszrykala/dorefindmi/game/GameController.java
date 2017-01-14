package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.AbcButtons;
import com.tomaszrykala.dorefindmi.things.AbcLeds;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;

public class GameController implements PadListener {

    private final AbcButtons abcButtons;
    private final AbcLeds abcLeds;
    private final DigiDisplayController digiDisplayController;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtons abcButtons, AbcLeds abcLeds,
                          DigiDisplayController digiDisplayController, Timer timer, Game game) {
        this.abcButtons = abcButtons;
        this.abcLeds = abcLeds;
        this.digiDisplayController = digiDisplayController;
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
            abcLeds.lightFor(abcButton);
            if (game.isWon()) {
                stop();
            }
        }
        return onPad; // TODO: ignored
    }

    private void restart() {
        abcButtons.setLastPressed(null);
        abcButtons.enable();
        abcLeds.reset();
        timer.start();
        game.start();
    }

    private void start() {
        digiDisplayController.displayBlocking("3...", 200);
        digiDisplayController.displayBlocking("2...", 200);
        digiDisplayController.displayBlocking("1...", 200);
        digiDisplayController.displayBlocking("GO !", 500);
        restart();
    }

    private void stop() {
        timer.stop();
        abcButtons.disable();

        // TODO: ??
        digiDisplayController.display("HAT!");
//        digiDisplayController.display("WON");
//        digiDisplayController.display(String.valueOf(timer.get()));
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public boolean isWon() {
        return game.isWon();
    }
}
