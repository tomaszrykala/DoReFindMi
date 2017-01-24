package com.tomaszrykala.dorefindmi;

import android.app.Activity;
import android.os.Bundle;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.RealGenerator;
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController;
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController;
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController;
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.RealAbcButtonsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.RealAbcLedsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.RealBuzzerSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.RealDigiDisplaySupplier;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.RealLedStripSupplier;

public class DoReFindMiActivity extends Activity {

    // HAT Things
    private RealAbcButtonsSupplier abcButtonsSupplier = new RealAbcButtonsSupplier();
    private RealAbcLedsSupplier abcLedsSupplier = new RealAbcLedsSupplier();
    private RealDigiDisplaySupplier digiDisplaySupplier = new RealDigiDisplaySupplier();
    private RealLedStripSupplier ledStripSupplier = new RealLedStripSupplier();
    private RealBuzzerSupplier realBuzzerSupplier = new RealBuzzerSupplier();

    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // *things' controllers
        final AbcButtonsController abcButtonsController = new AbcButtonsController(abcButtonsSupplier);
        final AbcLedsController abcLedsController = new AbcLedsController(abcLedsSupplier);
        final DigiDisplayController digiDisplayController = new DigiDisplayController(digiDisplaySupplier);
        final LedStripController ledStripController = new LedStripController(ledStripSupplier);
        final BuzzerController buzzerController = new BuzzerController(realBuzzerSupplier);

        gameController = new GameController(
                abcButtonsController,
                abcLedsController,
                digiDisplayController,
                new Timer(digiDisplayController),
                new Game(
                        ledStripController,
                        buzzerController,
                        new RealGenerator()));
    }

    @Override
    protected void onDestroy() {
        gameController.onDestroy();
        super.onDestroy();
    }
}
