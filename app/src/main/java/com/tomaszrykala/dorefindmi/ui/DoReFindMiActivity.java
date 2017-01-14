package com.tomaszrykala.dorefindmi.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.StepsGenerator;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.things.AbcButtons;
import com.tomaszrykala.dorefindmi.things.AbcLeds;
import com.tomaszrykala.dorefindmi.things.Buzzer;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;
import com.tomaszrykala.dorefindmi.things.LedStrip;
import com.tomaszrykala.dorefindmi.things.suppliers.abcbutton.RealAbcButtonSupplier;
import com.tomaszrykala.dorefindmi.things.suppliers.abcled.RealAbcLedSupplier;
import com.tomaszrykala.dorefindmi.things.suppliers.buzzer.RealBuzzerSupplier;
import com.tomaszrykala.dorefindmi.things.suppliers.digidisplay.RealDigiDisplaySupplier;
import com.tomaszrykala.dorefindmi.things.suppliers.ledstrip.RealLedStripSupplier;

public class DoReFindMiActivity extends Activity {

    // things - provided
    private final AbcButtons abcButtons = new AbcButtons(new RealAbcButtonSupplier());
    private final AbcLeds abcLeds = new AbcLeds(new RealAbcLedSupplier());
    private final DigiDisplayController digiDisplayController =
            new DigiDisplayController(new RealDigiDisplaySupplier());
    private final LedStrip ledStrip = new LedStrip(new RealLedStripSupplier());
    private final Buzzer buzzer = new Buzzer(new RealBuzzerSupplier());

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
            abcButtons.close();
            abcLeds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameController() {
        new GameController(
                abcButtons,
                abcLeds, digiDisplayController,
                new Timer(digiDisplayController),
                new Game(
                        new StepsGenerator(
                                // new RealGenerator() // TODO
                                new MockGenerator()
                        ).steps(), ledStrip, buzzer));
    }
}
