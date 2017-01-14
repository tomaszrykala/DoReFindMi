package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.AbcButtons;
import com.tomaszrykala.dorefindmi.things.AbcLeds;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;

public class GameController implements AbcButton.Listener {

    private final AbcButtons abcButtons;
    private final AbcLeds abcLeds;
    private final DigiDisplayController digiDisplay;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtons abcButtons, AbcLeds abcLeds, DigiDisplayController digiDisplayController,
                          Timer timer, Game game) {
        this.abcButtons = abcButtons;
        this.abcButtons.setListener(this);

        this.abcLeds = abcLeds;
        digiDisplay = digiDisplayController;

        this.timer = timer;
        this.game = game;

        start();
    }

    @Override public void onAbcButton(AbcButton abcButton) {
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
    }

    private void restart() {
        abcButtons.setLastPressed(null);
        abcButtons.enable();
        abcLeds.reset();
        timer.start();
        game.start();
    }

    private void start() {
        digiDisplay.displayBlocking("3...", 200);
        digiDisplay.displayBlocking("2...", 200);
        digiDisplay.displayBlocking("1...", 200);
        digiDisplay.displayBlocking("GO !", 500);
        restart();
    }

    private void stop() {
        timer.stop();
        abcButtons.disable();

        // TODO: ??
        digiDisplay.display("HAT!");
//        digiDisplay.display("WON");
//        digiDisplay.display(String.valueOf(timer.get()));
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public boolean isWon() {
        return game.isWon();
    }
}
