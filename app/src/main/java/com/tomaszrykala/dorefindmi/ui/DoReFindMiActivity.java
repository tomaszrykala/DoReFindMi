package com.tomaszrykala.dorefindmi.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController;
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController;
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController;
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.RealAbcButtonsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.RealAbcLedsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.RealBuzzerSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.RealDigiDisplaySupplier;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.RealLedStripSupplier;

public class DoReFindMiActivity extends Activity {

    // things - provided
    private final AbcButtonsController abcButtonsController = new AbcButtonsController(new RealAbcButtonsSupplier());
    private final AbcLedsController abcLedsController = new AbcLedsController(new RealAbcLedsSupplier());
    private final DigiDisplayController digiDisplayController =
            new DigiDisplayController(new RealDigiDisplaySupplier());
    private final LedStripController ledStripController = new LedStripController(new RealLedStripSupplier());
    private final BuzzerController buzzerController = new BuzzerController(new RealBuzzerSupplier());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGameController();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeHat();
    }

    private void closeHat() {
        try {
            abcButtonsController.close();
            abcLedsController.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameController() {
        new GameController(
                abcButtonsController,
                abcLedsController,
                digiDisplayController,
                new Timer(digiDisplayController),
                new Game(
                        // new RealGenerator() // TODO
                        new MockGenerator().getSteps(), ledStripController, buzzerController));
    }
}
