package com.tomaszrykala.dorefindmi

import android.graphics.Color
import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.domain.AbcLed
import com.tomaszrykala.dorefindmi.domain.Note.*
import com.tomaszrykala.dorefindmi.domain.Step
import com.tomaszrykala.dorefindmi.game.Game
import com.tomaszrykala.dorefindmi.game.GameController
import com.tomaszrykala.dorefindmi.game.Timer
import com.tomaszrykala.dorefindmi.game.generator.MockGenerator
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.NonBlockingDigiDisplayController
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.MockAbcButtonsSupplier
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.MockAbcLedsSupplier
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.MockBuzzerSupplier
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.MockDigiDisplaySupplier
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.MockLedStripSupplier
import org.junit.Assert.*
import org.junit.Test

class DoReFindMiTests {

    @Test
    @Throws(Exception::class)
    fun notesShouldHaveExpectedValues() {
        assertEquals(261.626, DO_LO.pitch, 0.0)
        assertEquals(0, DO_LO.order)
        assertEquals(Color.YELLOW, DO_LO.led.color)

        assertEquals(293.665, RE.pitch, 0.0)
        assertEquals(1, RE.order)
        assertEquals(Color.GREEN, RE.led.color)

        assertEquals(329.628, MI.pitch, 0.0)
        assertEquals(2, MI.order)
        assertEquals(Color.CYAN, MI.led.color)

        assertEquals(349.228, FA.pitch, 0.0)
        assertEquals(3, FA.order)
        assertEquals(Color.BLUE, FA.led.color)

        assertEquals(391.995, SO.pitch, 0.0)
        assertEquals(4, SO.order)
        assertEquals(Color.LTGRAY, SO.led.color)

        assertEquals(440.0, LA.pitch, 0.0)
        assertEquals(5, LA.order)
        assertEquals(Color.MAGENTA, LA.led.color)

        assertEquals(493.883, SI.pitch, 0.0)
        assertEquals(6, SI.order)
        assertEquals(Color.RED, SI.led.color)

        assertEquals(523.251, DO_HI.pitch, 0.0)
        assertEquals(7, DO_HI.order)
        assertEquals(Color.WHITE, DO_HI.led.color)

        assertEquals(110.0, MISS.pitch, 0.0)
        assertEquals(-1, MISS.order)
        assertEquals(Color.BLACK, MISS.led.color)
    }

    @Test
    fun padsShouldHaveExpectedValues() {
        assertEquals("A", AbcButton.A.value)
        assertEquals("B", AbcButton.B.value)
        assertEquals("C", AbcButton.C.value)
    }

    @Test
    fun stepShouldHaveExpectedValues() {
        val step = Step(AbcButton.A, DO_LO)
        assertEquals(AbcButton.A, step.abcButton)
        assertEquals(DO_LO, step.note)
    }

    @Test
    fun stepGeneratorShouldGenerateExpectedSteps() {
        val steps = MockGenerator().steps
        assertEquals(8, steps.size)
        assertEquals(DO_LO, steps[0].note)
        assertEquals(RE, steps[1].note)
        assertEquals(MI, steps[2].note)
        assertEquals(FA, steps[3].note)
        assertEquals(SO, steps[4].note)
        assertEquals(LA, steps[5].note)
        assertEquals(SI, steps[6].note)
        assertEquals(DO_HI, steps[7].note)
    }

    @Test
    fun gameShouldHaveCorrectStateWhenGameIsWon() {
        val ledStripController = LedStripController(MockLedStripSupplier())
        val buzzerController = BuzzerController(MockBuzzerSupplier())
        val mockGenerator = MockGenerator()

        val game = Game(ledStripController, buzzerController, mockGenerator)
        assertFalse(game.isStarted)
        game.start()

        val steps = mockGenerator.steps
        for (i in steps.indices) {
            val step = steps[i]
            val note = step.note

            assertTrue(game.onPad(step.abcButton))
            assertTrue(ledStripController.isLitAt(note.led))
            assertTrue(buzzerController.lastBuzzedAt(note))
        }
        assertTrue(game.isWon)
        assertFalse(game.isStarted)
    }

    @Test
    fun gameControllerShouldHaveCorrectStateWhenGameIsWon() {
        val abcButtonsController = AbcButtonsController(MockAbcButtonsSupplier())
        val digiDisplayController = NonBlockingDigiDisplayController(MockDigiDisplaySupplier())
        val mockGenerator = MockGenerator()
        val timer = Timer(digiDisplayController)

        val abcLedsController = AbcLedsController(MockAbcLedsSupplier())

        val gameController = GameController(
                abcButtonsController,
                abcLedsController, digiDisplayController,
                timer,
                Game(LedStripController(MockLedStripSupplier()),
                        BuzzerController(MockBuzzerSupplier()),
                        mockGenerator)
        )

        gameController.startRound()

        assertTrue(gameController.isStarted)

        val steps = mockGenerator.steps

        for (i in steps.indices) {
            val abcButton = steps[i].abcButton
            gameController.onAbcButton(abcButton)
            assertTrue(abcButtonsController.isLastPressed(abcButton))
            if (i < steps.size - 1) {
                assertTrue(abcButtonsController.isEnabled)
                assertTrue(digiDisplayController.isRunning)
            }
        }

        assertTrue(gameController.isWon)
        assertFalse(gameController.isStarted)
        assertFalse(abcButtonsController.isEnabled)
        assertFalse(digiDisplayController.isRunning)
    }

    @Test
    fun gameControllerShouldHaveCorrectStateWhenGameIsWonAfterAFewMisses() {
        val mockGenerator = MockGenerator()
        val abcButtonsController = AbcButtonsController(MockAbcButtonsSupplier())
        val digiDisplayController = NonBlockingDigiDisplayController(MockDigiDisplaySupplier())
        val timer = Timer(digiDisplayController)

        val abcLedsController = AbcLedsController(MockAbcLedsSupplier())

        val gameController = GameController(
                abcButtonsController,
                abcLedsController, digiDisplayController,
                timer,
                Game(LedStripController(MockLedStripSupplier()),
                        BuzzerController(MockBuzzerSupplier()),
                        mockGenerator)
        )

        gameController.startRound()

        assertTrue(gameController.isStarted)

        val steps = mockGenerator.steps

        // hit
        val stepHit = steps[0]
        gameController.onAbcButton(stepHit.abcButton)
        assertTrue(abcLedsController.isLitAt(AbcLed.A))
        assertTrue(abcButtonsController.isLastPressed(stepHit.abcButton))

        // miss
        val stepMiss = steps[2]
        gameController.onAbcButton(stepMiss.abcButton)
        assertFalse(abcLedsController.isLitAt(AbcLed.A))
        assertFalse(abcButtonsController.hasLastPressed())

        // hit
        val stepHitTwo = steps[0]
        gameController.onAbcButton(stepHitTwo.abcButton)
        assertTrue(abcLedsController.isLitAt(AbcLed.A))
        assertTrue(abcButtonsController.isLastPressed(stepHitTwo.abcButton))

        // hit
        val stepHitThree = steps[1]
        gameController.onAbcButton(stepHitThree.abcButton)
        assertFalse(abcLedsController.isLitAt(AbcLed.A))
        assertTrue(abcLedsController.isLitAt(AbcLed.B))
        assertTrue(abcButtonsController.isLastPressed(stepHitThree.abcButton))

        // miss
        val stepMissTwo = steps[3]
        gameController.onAbcButton(stepMissTwo.abcButton)
        assertFalse(abcLedsController.isLitAt(AbcLed.A))
        assertFalse(abcLedsController.isLitAt(AbcLed.B))
        assertFalse(abcButtonsController.hasLastPressed())

        for (i in steps.indices) {
            val abcButton = steps[i].abcButton
            assertTrue(digiDisplayController.isRunning)
            gameController.onAbcButton(abcButton)
            when (abcButton) {
                AbcButton.A -> assertTrue(abcLedsController.isLitAt(AbcLed.A))
                AbcButton.B -> assertTrue(abcLedsController.isLitAt(AbcLed.B))
                AbcButton.C -> assertTrue(abcLedsController.isLitAt(AbcLed.C))
            }
            assertTrue(abcButtonsController.isLastPressed(abcButton))
            if (i < steps.size - 1) assertTrue(abcButtonsController.isEnabled)
        }

        assertTrue(gameController.isWon)
        assertFalse(gameController.isStarted)
        assertFalse(abcButtonsController.isEnabled)
        assertFalse(digiDisplayController.isRunning)
    }
}