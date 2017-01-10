package com.tomaszrykala.dorefindmi.ui;

import android.app.Activity;
import android.os.Bundle;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.StepsGenerator;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.things.AbcButtonLeds;
import com.tomaszrykala.dorefindmi.things.AbcButtonPads;
import com.tomaszrykala.dorefindmi.things.Buzzer;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;
import com.tomaszrykala.dorefindmi.things.LedStrip;

public class DoReFindMiActivity extends Activity {

    // things - provided
    private final AbcButtonPads abcButtonPads = new AbcButtonPads(true);
    private final AbcButtonLeds abcButtonLeds = new AbcButtonLeds(true);
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
            abcButtonPads.close();
            abcButtonLeds.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGameController() {
        new GameController(
                abcButtonPads,
                abcButtonLeds, digitalDisplay,
                new Timer(digitalDisplay),
                new Game(
                        new StepsGenerator(
                                // new RealGenerator() // TODO
                                new MockGenerator()
                        ).steps(), ledStrip, buzzer));
    }
}
