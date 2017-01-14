package com.tomaszrykala.dorefindmi;

import com.tomaszrykala.dorefindmi.game.Game;
import com.tomaszrykala.dorefindmi.game.GameController;
import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.game.StepsGenerator;
import com.tomaszrykala.dorefindmi.game.Timer;
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator;
import com.tomaszrykala.dorefindmi.game.generator.RealGenerator;
import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.AbcButtons;
import com.tomaszrykala.dorefindmi.things.AbcLeds;
import com.tomaszrykala.dorefindmi.things.Buzzer;
import com.tomaszrykala.dorefindmi.things.DigitalDisplay;
import com.tomaszrykala.dorefindmi.things.LedStrip;
import com.tomaszrykala.dorefindmi.things.suppliers.abcbutton.MockAbcButtonSupplier;
import com.tomaszrykala.dorefindmi.things.suppliers.abcled.MockAbcLedSupplier;
import com.tomaszrykala.dorefindmi.things.suppliers.buzzer.MockBuzzerSupplier;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import static com.tomaszrykala.dorefindmi.model.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.model.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.model.Note.FA;
import static com.tomaszrykala.dorefindmi.model.Note.LA;
import static com.tomaszrykala.dorefindmi.model.Note.MI;
import static com.tomaszrykala.dorefindmi.model.Note.RE;
import static com.tomaszrykala.dorefindmi.model.Note.SI;
import static com.tomaszrykala.dorefindmi.model.Note.SO;

public class ExampleUnitTest {

    // TODO: Mock
    private LedStrip ledStrip = new LedStrip();
    private Buzzer buzzer = new Buzzer(new MockBuzzerSupplier());
    // private Game game = new Game(StepsGenerator.steps(), new LedStrip(), new Buzzer());

    @Test
    public void testNotes() throws Exception {
        Assert.assertEquals("DO_LO", DO_LO.pitch);
        Assert.assertEquals("0", DO_LO.led.toString());

        Assert.assertEquals("RE", RE.pitch);
        Assert.assertEquals("1", RE.led.toString());

        Assert.assertEquals("MI", MI.pitch);
        Assert.assertEquals("2", MI.led.toString());

        Assert.assertEquals("FA", FA.pitch);
        Assert.assertEquals("3", FA.led.toString());

        Assert.assertEquals("SO", SO.pitch);
        Assert.assertEquals("4", SO.led.toString());

        Assert.assertEquals("LA", Note.LA.pitch);
        Assert.assertEquals("5", Note.LA.led.toString());

        Assert.assertEquals("SI", Note.SI.pitch);
        Assert.assertEquals("6", Note.SI.led.toString());

        Assert.assertEquals("DO_HI", Note.DO_HI.pitch);
        Assert.assertEquals("ALL", Note.DO_HI.led.toString());

        Assert.assertEquals("MISS", Note.MISS.pitch);
        Assert.assertNull(Note.MISS.led.toString());
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
        final List<Step> steps = givenARealStepsGenerator();
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
        final List<Step> steps = givenARealStepsGenerator();
        final Game game = new Game(steps, ledStrip, buzzer);
        Assert.assertTrue(game.isStarted());
        for (int i = 0; i < steps.size(); i++) {
            final Step step = steps.get(i);
            final Note note = step.getNote();

            Assert.assertTrue(game.onPad(step.getAbcButton()));
            Assert.assertTrue(ledStrip.isLitAt(note.led));
            Assert.assertTrue(buzzer.lastBuzzedAt(note));
        }
        Assert.assertTrue(game.isWon());
        Assert.assertFalse(game.isStarted());
    }

    @Test
    public void testGameController_whenWon() {
        final List<Step> steps = givenARealStepsGenerator();
        final AbcButtons abcButtons = new AbcButtons(new MockAbcButtonSupplier());
        final DigitalDisplay digitalDisplay = new DigitalDisplay();
        final Timer timer = new Timer(digitalDisplay);

        final AbcLeds abcLeds = new AbcLeds(new MockAbcLedSupplier()); // TODO

        final GameController gameController = new GameController(
                abcButtons,
                abcLeds, digitalDisplay,
                timer,
                new Game(steps, new LedStrip(), new Buzzer(new MockBuzzerSupplier()))
        );

        Assert.assertTrue(gameController.isStarted());

        for (int i = 0; i < steps.size(); i++) {
            final AbcButton abcButton = steps.get(i).getAbcButton();
            Assert.assertTrue(digitalDisplay.isRunning());
            Assert.assertTrue(gameController.onPad(abcButton));
            Assert.assertTrue(abcButtons.isLastPressed(abcButton));
            if (i < steps.size() - 1) Assert.assertTrue(abcButtons.isEnabled());
        }

        Assert.assertTrue(gameController.isWon());
        Assert.assertFalse(abcButtons.isEnabled());
        Assert.assertFalse(digitalDisplay.isRunning());
        Assert.assertFalse(gameController.isStarted());
        Assert.assertEquals(digitalDisplay.get(), timer.get());
    }

    @Test
    public void testGameController_whenHitMissHitHitMissWon() {
        final List<Step> steps = givenAMockStepsGenerator();
        final AbcButtons abcButtons = new AbcButtons(new MockAbcButtonSupplier());
        final DigitalDisplay digitalDisplay = new DigitalDisplay();
        final Timer timer = new Timer(digitalDisplay);

        final AbcLeds abcLeds = new AbcLeds(new MockAbcLedSupplier()); // TODO

        final GameController gameController = new GameController(
                abcButtons,
                abcLeds, digitalDisplay,
                timer,
                new Game(steps, new LedStrip(), new Buzzer(new MockBuzzerSupplier()))
        );

        Assert.assertTrue(gameController.isStarted());

        // hit
        final Step stepHit = steps.get(0);
        Assert.assertTrue(gameController.onPad(stepHit.getAbcButton()));
        Assert.assertTrue(abcButtons.isLastPressed(stepHit.getAbcButton()));

        // miss
        final Step stepMiss = steps.get(2);
        Assert.assertFalse(gameController.onPad(stepMiss.getAbcButton()));
        Assert.assertFalse(abcButtons.hasLastPressed());

        // hit
        final Step stepHitTwo = steps.get(0);
        Assert.assertTrue(gameController.onPad(stepHitTwo.getAbcButton()));
        Assert.assertTrue(abcButtons.isLastPressed(stepHitTwo.getAbcButton()));

        // hit
        final Step stepHitThree = steps.get(1);
        Assert.assertTrue(gameController.onPad(stepHitThree.getAbcButton()));
        Assert.assertTrue(abcButtons.isLastPressed(stepHitThree.getAbcButton()));

        // miss
        final Step stepMissTwo = steps.get(3);
        Assert.assertFalse(gameController.onPad(stepMissTwo.getAbcButton()));
        Assert.assertFalse(abcButtons.hasLastPressed());


        for (int i = 0; i < steps.size(); i++) {
            final AbcButton abcButton = steps.get(i).getAbcButton();
            Assert.assertTrue(digitalDisplay.isRunning());
            Assert.assertTrue(gameController.onPad(abcButton));
            Assert.assertTrue(abcButtons.isLastPressed(abcButton));
            if (i < steps.size() - 1) Assert.assertTrue(abcButtons.isEnabled());
        }

        Assert.assertTrue(gameController.isWon());
        Assert.assertFalse(abcButtons.isEnabled());
        Assert.assertFalse(digitalDisplay.isRunning());
        Assert.assertFalse(gameController.isStarted());
        Assert.assertEquals(digitalDisplay.get(), timer.get());
    }

    private List<Step> givenARealStepsGenerator() {
        return new StepsGenerator(new RealGenerator()).steps();
    }

    private List<Step> givenAMockStepsGenerator() {
        return new StepsGenerator(new MockGenerator()).steps();
    }
}