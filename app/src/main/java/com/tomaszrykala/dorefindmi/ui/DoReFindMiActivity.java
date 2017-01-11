package com.tomaszrykala.dorefindmi.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.StepsGenerator;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.things.AbcLeds;
import com.tomaszrykala.dorefindmi.things.AbcButtons;
import com.tomaszrykala.dorefindmi.things.Buzzer;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;
import com.tomaszrykala.dorefindmi.things.LedStrip;

public class DoReFindMiActivity extends Activity {

    // things - provided
    private final AbcButtons abcButtons = new AbcButtons(new AbcButtons.RealAbcButtonSupplier());
    private final AbcLeds abcLeds = new AbcLeds(new AbcLeds.RealAbcLedSupplier());
    private final DigitalDisplay digitalDisplay = new DigitalDisplay();
    private final LedStrip ledStrip = new LedStrip();
    private final Buzzer buzzer = new Buzzer();

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
                abcLeds, digitalDisplay,
                new Timer(digitalDisplay),
                new Game(
                        new StepsGenerator(
                                // new RealGenerator() // TODO
                                new MockGenerator()
                        ).steps(), ledStrip, buzzer));
    }
}
