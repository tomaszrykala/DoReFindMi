package com.tomaszrykala.dorefindmi;

import android.graphics.Color;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.AbcLed;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController;
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController;
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController;
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.NonBlockingDigiDisplayController;
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController;
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.MockAbcButtonsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.MockAbcLedsSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.MockBuzzerSupplier;
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.MockDigiDisplaySupplier;
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.MockLedStripSupplier;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import static com.tomaszrykala.dorefindmi.model.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.model.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.model.Note.FA;
import static com.tomaszrykala.dorefindmi.model.Note.LA;
import static com.tomaszrykala.dorefindmi.model.Note.MI;
import static com.tomaszrykala.dorefindmi.model.Note.MISS;
import static com.tomaszrykala.dorefindmi.model.Note.RE;
import static com.tomaszrykala.dorefindmi.model.Note.SI;
import static com.tomaszrykala.dorefindmi.model.Note.SO;

public class DoReFindMiTests {

    @Test
    public void testNotes() throws Exception {
        Assert.assertEquals(261.626, DO_LO.pitch);
        Assert.assertEquals(0, DO_LO.order);
        Assert.assertEquals(Color.YELLOW, DO_LO.led.color);

        Assert.assertEquals(293.665, RE.pitch);
        Assert.assertEquals(1, RE.order);
        Assert.assertEquals(Color.GREEN, RE.led.color);

        Assert.assertEquals(329.628, MI.pitch);
        Assert.assertEquals(2, MI.order);
        Assert.assertEquals(Color.CYAN, MI.led.color);

        Assert.assertEquals(349.228, FA.pitch);
        Assert.assertEquals(3, FA.order);
        Assert.assertEquals(Color.BLUE, FA.led.color);

        Assert.assertEquals(391.995, SO.pitch);
        Assert.assertEquals(4, SO.order);
        Assert.assertEquals(Color.LTGRAY, SO.led.color);

        Assert.assertEquals(440.0, Note.LA.pitch);
        Assert.assertEquals(5, LA.order);
        Assert.assertEquals(Color.MAGENTA, Note.LA.led.color);

        Assert.assertEquals(493.883, Note.SI.pitch);
        Assert.assertEquals(6, SI.order);
        Assert.assertEquals(Color.RED, Note.SI.led.color);

        Assert.assertEquals(523.251, Note.DO_HI.pitch);
        Assert.assertEquals(7, DO_HI.order);
        Assert.assertEquals(Color.WHITE, Note.DO_HI.led.color);

        Assert.assertEquals(110.0, Note.MISS.pitch);
        Assert.assertEquals(-1, MISS.order);
        Assert.assertEquals(Color.BLACK, Note.MISS.led.color);
    }

    @Test
    public void testPads() {
        Assert.assertEquals("A", AbcButton.A.value);
        Assert.assertEquals("B", AbcButton.B.value);
        Assert.assertEquals("C", AbcButton.C.value);
    }

    @Test
    public void testSteps() {
        final Step step = new Step(AbcButton.A, DO_LO);
        Assert.assertEquals(AbcButton.A, step.getAbcButton());
        Assert.assertEquals(DO_LO, step.getNote());
    }

    @Test
    public void testStepsGenerator() {
        final List<Step> steps = new MockGenerator().getSteps();
        Assert.assertEquals(8, steps.size());
        Assert.assertEquals(DO_LO, steps.get(0).getNote());
        Assert.assertEquals(RE, steps.get(1).getNote());
        Assert.assertEquals(MI, steps.get(2).getNote());
        Assert.assertEquals(FA, steps.get(3).getNote());
        Assert.assertEquals(SO, steps.get(4).getNote());
        Assert.assertEquals(LA, steps.get(5).getNote());
        Assert.assertEquals(SI, steps.get(6).getNote());
        Assert.assertEquals(DO_HI, steps.get(7).getNote());
    }

    @Test
    public void testGame_wonInOne() {
        final LedStripController ledStripController = new LedStripController(new MockLedStripSupplier());
        final BuzzerController buzzerController = new BuzzerController(new MockBuzzerSupplier());
        final MockGenerator mockGenerator = new MockGenerator();

        final Game game = new Game(ledStripController, buzzerController, mockGenerator);
        Assert.assertFalse(game.isStarted());
        game.start();

        final List<Step> steps = mockGenerator.getSteps();
        for (int i = 0; i < steps.size(); i++) {
            final Step step = steps.get(i);
            final Note note = step.getNote();

            Assert.assertTrue(game.onPad(step.getAbcButton()));
            Assert.assertTrue(ledStripController.isLitAt(note.led));
            Assert.assertTrue(buzzerController.lastBuzzedAt(note));
        }
        Assert.assertTrue(game.isWon());
        Assert.assertFalse(game.isStarted());
    }

    @Test
    public void testGameController_whenWon() {
        final AbcButtonsController abcButtonsController = new AbcButtonsController(new MockAbcButtonsSupplier());
        final DigiDisplayController digiDisplayController = new NonBlockingDigiDisplayController(new MockDigiDisplaySupplier());
        final MockGenerator mockGenerator = new MockGenerator();
        final Timer timer = new Timer(digiDisplayController);

        final AbcLedsController abcLedsController = new AbcLedsController(new MockAbcLedsSupplier());

        final GameController gameController = new GameController(
                abcButtonsController,
                abcLedsController, digiDisplayController,
                timer,
                new Game(new LedStripController(new MockLedStripSupplier()),
                        new BuzzerController(new MockBuzzerSupplier()),
                        mockGenerator)
        );

        Assert.assertTrue(gameController.isStarted());

        final List<Step> steps = mockGenerator.getSteps();

        for (int i = 0; i < steps.size(); i++) {
            final AbcButton abcButton = steps.get(i).getAbcButton();
            gameController.onAbcButton(abcButton);
            Assert.assertTrue(abcButtonsController.isLastPressed(abcButton));
            if (i < steps.size() - 1) {
                Assert.assertTrue(abcButtonsController.isEnabled());
                Assert.assertTrue(digiDisplayController.isRunning());
            }
        }

        Assert.assertTrue(gameController.isWon());
        Assert.assertFalse(gameController.isStarted());
        Assert.assertFalse(abcButtonsController.isEnabled());
        Assert.assertFalse(digiDisplayController.isRunning());
    }

    @Test
    public void testGameController_whenHitMissHitHitMissWon() {
        final MockGenerator mockGenerator = new MockGenerator();
        final AbcButtonsController abcButtonsController = new AbcButtonsController(new MockAbcButtonsSupplier());
        final DigiDisplayController digiDisplayController = new NonBlockingDigiDisplayController(new MockDigiDisplaySupplier());
        final Timer timer = new Timer(digiDisplayController);

        final AbcLedsController abcLedsController = new AbcLedsController(new MockAbcLedsSupplier());

        final GameController gameController = new GameController(
                abcButtonsController,
                abcLedsController, digiDisplayController,
                timer,
                new Game(new LedStripController(new MockLedStripSupplier()),
                        new BuzzerController(new MockBuzzerSupplier()),
                        mockGenerator)
        );

        Assert.assertTrue(gameController.isStarted());

        final List<Step> steps = mockGenerator.getSteps();

        // hit
        final Step stepHit = steps.get(0);
        gameController.onAbcButton(stepHit.getAbcButton());
        Assert.assertTrue(abcLedsController.isLitAt(AbcLed.A));
        Assert.assertTrue(abcButtonsController.isLastPressed(stepHit.getAbcButton()));

        // miss
        final Step stepMiss = steps.get(2);
        gameController.onAbcButton(stepMiss.getAbcButton());
        Assert.assertFalse(abcLedsController.isLitAt(AbcLed.A));
        Assert.assertFalse(abcButtonsController.hasLastPressed());

        // hit
        final Step stepHitTwo = steps.get(0);
        gameController.onAbcButton(stepHitTwo.getAbcButton());
        Assert.assertTrue(abcLedsController.isLitAt(AbcLed.A));
        Assert.assertTrue(abcButtonsController.isLastPressed(stepHitTwo.getAbcButton()));

        // hit
        final Step stepHitThree = steps.get(1);
        gameController.onAbcButton(stepHitThree.getAbcButton());
        Assert.assertFalse(abcLedsController.isLitAt(AbcLed.A));
        Assert.assertTrue(abcLedsController.isLitAt(AbcLed.B));
        Assert.assertTrue(abcButtonsController.isLastPressed(stepHitThree.getAbcButton()));

        // miss
        final Step stepMissTwo = steps.get(3);
        gameController.onAbcButton(stepMissTwo.getAbcButton());
        Assert.assertFalse(abcLedsController.isLitAt(AbcLed.A));
        Assert.assertFalse(abcLedsController.isLitAt(AbcLed.B));
        Assert.assertFalse(abcButtonsController.hasLastPressed());


        for (int i = 0; i < steps.size(); i++) {
            final AbcButton abcButton = steps.get(i).getAbcButton();
            Assert.assertTrue(digiDisplayController.isRunning());
            gameController.onAbcButton(abcButton);
            switch (abcButton) {
                case A:
                    Assert.assertTrue(abcLedsController.isLitAt(AbcLed.A));
                    break;
                case B:
                    Assert.assertTrue(abcLedsController.isLitAt(AbcLed.B));
                    break;
                case C:
                    Assert.assertTrue(abcLedsController.isLitAt(AbcLed.C));
                    break;
            }
            Assert.assertTrue(abcButtonsController.isLastPressed(abcButton));
            if (i < steps.size() - 1) Assert.assertTrue(abcButtonsController.isEnabled());
        }

        Assert.assertTrue(gameController.isWon());
        Assert.assertFalse(gameController.isStarted());
        Assert.assertFalse(abcButtonsController.isEnabled());
        Assert.assertFalse(digiDisplayController.isRunning());
    }
}