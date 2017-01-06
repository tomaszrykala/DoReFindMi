package com.tomaszrykala.dorefindmi;

import junit.framework.Assert;

import org.junit.Test;

import java.util.List;

import static com.tomaszrykala.dorefindmi.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.Note.FA;
import static com.tomaszrykala.dorefindmi.Note.LA;
import static com.tomaszrykala.dorefindmi.Note.MI;
import static com.tomaszrykala.dorefindmi.Note.RE;
import static com.tomaszrykala.dorefindmi.Note.SI;
import static com.tomaszrykala.dorefindmi.Note.SO;

public class ExampleUnitTest {

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
        Assert.assertEquals("A", Pad.A.value);
        Assert.assertEquals("B", Pad.B.value);
        Assert.assertEquals("C", Pad.C.value);
    }

    @Test
    public void testSteps() {
        final Step step = new Step(Pad.A, DO_LO);
        Assert.assertEquals(Pad.A, step.getPad());
        Assert.assertEquals(DO_LO, step.getNote());
    }

    @Test
    public void testStepsGenerator() {
        final List<Step> steps = StepsGenerator.steps();
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
    public void testGame() {
        final List<Step> steps = StepsGenerator.steps();
        final Game game = new Game(steps);
        Assert.assertTrue(game.isStarted());
        for (int i = 0; i < steps.size(); i++) {
            final Step step = steps.get(i);
            Assert.assertTrue(game.onPad(step.getPad()));
        }
        Assert.assertTrue(game.isWon());
        Assert.assertFalse(game.isStarted());
    }
}