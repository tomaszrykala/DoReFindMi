package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController;
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;

public class GameController implements AbcButton.Listener {

    private final AbcButtonsController abcButtons;
    private final AbcLedsController abcLeds;
    private final DigiDisplayController digiDisplay;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtonsController abcButtonsController,
                          AbcLedsController abcLedsController,
                          DigiDisplayController digiDisplayController,
                          Timer timer, Game game) {

        abcButtons = abcButtonsController;
        abcButtons.setListener(this);
        abcLeds = abcLedsController;
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

    private void start() {
        showStarter();
        timer.start();
        restart();
    }

    private void showStarter() {
        final int halfASecond = 500;
        digiDisplay.displayBlocking("3...", halfASecond);
        digiDisplay.displayBlocking("2...", halfASecond);
        digiDisplay.displayBlocking("1...", halfASecond);
        digiDisplay.displayBlocking(" GO ", halfASecond);
    }

    private void restart() {
        abcButtons.setLastPressed(null);
        abcButtons.enable();
        abcLeds.reset();
        game.start();
    }

    private void stop() {
        timer.stop();
        abcButtons.disable();
        digiDisplay.displayBlocking("WON ", 2000);
        digiDisplay.onTick((int) digiDisplay.getCounter());
    }

    public boolean isStarted() {
        return game.isStarted();
    }

    public boolean isWon() {
        return game.isWon();
    }

    public void onDestroy() {
        try {
            abcButtons.close();
            abcLeds.close();
            digiDisplay.close();
            game.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
