package com.tomaszrykala.dorefindmi.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;
import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.StepsGenerator;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.Generator;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.model.Pad;
import com.tomaszrykala.dorefindmi.things.AbcButtonsPad;
import com.tomaszrykala.dorefindmi.things.Buzzer;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;
import com.tomaszrykala.dorefindmi.things.LedStrip;

import java.io.IOException;

public class DoReFindMiActivity extends Activity {

    private static final String TAG = DoReFindMiActivity.class.getSimpleName();

    // things - provided
    private final AbcButtonsPad abcButtonsPad = new AbcButtonsPad();
    private final DigitalDisplay digitalDisplay = new DigitalDisplay();
    private final LedStrip ledStrip = new LedStrip();
    private final Buzzer buzzer = new Buzzer();

    // generator - provided
//    private Generator generator = new RealGenerator();
    private Generator generator = new MockGenerator();

    // only real activity dependency
    private GameController gameController;

    // real - should be injected into things]
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Gpio ledA;
    private Gpio ledB;
    private Gpio ledC;
    private final Button.OnButtonEventListener onButtonEventListener = new Button.OnButtonEventListener() {
        @Override
        public void onButtonEvent(Button button, boolean pressed) {
            if (pressed) {
                Log.d(TAG, "button " + button.toString());

                boolean onPadA = false;
                boolean onPadB = false;
                boolean onPadC = false;
                if (button == buttonA) {
                    onPadA = gameController.onPad(Pad.A);
                } else if (button == buttonB) {
                    onPadB = gameController.onPad(Pad.B);
                } else if (button == buttonC) {
                    onPadC = gameController.onPad(Pad.C);
                }
                try {
                    ledA.setValue(onPadA);
                    ledB.setValue(onPadB);
                    ledC.setValue(onPadC);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGameController();
        initButtons();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        closeHat();
    }

    private void initButtons() {
        try {
            buttonA = RainbowHat.openButton(RainbowHat.BUTTON_A);
            buttonB = RainbowHat.openButton(RainbowHat.BUTTON_B);
            buttonC = RainbowHat.openButton(RainbowHat.BUTTON_C);

            buttonA.setOnButtonEventListener(onButtonEventListener);
            buttonB.setOnButtonEventListener(onButtonEventListener);
            buttonC.setOnButtonEventListener(onButtonEventListener);

            ledA = RainbowHat.openLed(RainbowHat.LED_RED);
            ledB = RainbowHat.openLed(RainbowHat.LED_GREEN);
            ledC = RainbowHat.openLed(RainbowHat.LED_BLUE);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeHat() {
        try {
            buttonA.close();
            buttonB.close();
            buttonC.close();
            ledA.close();
            ledB.close();
            ledC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGameController() {
        gameController = new GameController(
                abcButtonsPad,
                digitalDisplay,
                new Timer(digitalDisplay),
                new Game(new StepsGenerator(generator).steps(), ledStrip, buzzer));
    }
}
