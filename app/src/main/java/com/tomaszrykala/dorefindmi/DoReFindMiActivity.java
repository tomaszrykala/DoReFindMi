package com.tomaszrykala.dorefindmi;

import android.app.Activity;
import android.os.Bundle;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.GeneratorImpl;
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController;
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController;
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController;
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.AbcButtonsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.AbcButtonsSupplierImpl;
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.AbcLedsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.AbcLedsSupplierImpl;
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.BuzzerSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.BuzzerSupplierImpl;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplier;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplierImpl;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplierImpl;

public class DoReFindMiActivity extends Activity {

    // Android Things drivers
    private final AbcButtonsSupplier abcButtonsSupplier = new AbcButtonsSupplierImpl();
    private final AbcLedsSupplier abcLedsSupplier = new AbcLedsSupplierImpl();
    private final DigiDisplaySupplier digiDisplaySupplier = new DigiDisplaySupplierImpl();
    private final LedStripSupplier ledStripSupplier = new LedStripSupplierImpl();
    private final BuzzerSupplier buzzerSupplier = new BuzzerSupplierImpl();

    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // driver Controllers
        final AbcButtonsController abcButtonsController = new AbcButtonsController(abcButtonsSupplier);
        final AbcLedsController abcLedsController = new AbcLedsController(abcLedsSupplier);
        final DigiDisplayController digiDisplayController = new DigiDisplayController(digiDisplaySupplier);
        final LedStripController ledStripController = new LedStripController(ledStripSupplier);
        final BuzzerController buzzerController = new BuzzerController(buzzerSupplier);

        gameController = new GameController(
                abcButtonsController,
                abcLedsController,
                digiDisplayController,
                new Timer(digiDisplayController),
                new Game(
                        ledStripController,
                        buzzerController,
                        new GeneratorImpl()));
    }

    @Override
    protected void onDestroy() {
        gameController.onDestroy();
        super.onDestroy();
    }
}
