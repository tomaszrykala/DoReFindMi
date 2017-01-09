package com.tomaszrykala.dorefindmi;

class GameController implements PadListener {

    private final AbcButtonsPad abcButtonsPad;
    private final DigiDisplay digiDisplay;
    private final Timer timer;
    private final Game game;

    public GameController(AbcButtonsPad abcButtonsPad, DigiDisplay digiDisplay, Timer timer, Game game) {
        this.abcButtonsPad = abcButtonsPad;
        this.digiDisplay = digiDisplay;
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
