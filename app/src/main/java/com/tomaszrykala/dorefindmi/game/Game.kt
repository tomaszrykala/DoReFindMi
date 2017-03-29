package com.tomaszrykala.dorefindmi.game

import com.tomaszrykala.dorefindmi.game.generator.Generator
import com.tomaszrykala.dorefindmi.model.AbcButton
import com.tomaszrykala.dorefindmi.model.Note
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController
import java.util.concurrent.LinkedBlockingQueue

class Game(private val ledStripController: LedStripController,
           private val buzzerController: BuzzerController,
           private val generator: Generator) {

    private val stepQueue = LinkedBlockingQueue<Step>()

    private var steps: List<Step>? = null
    var isStarted: Boolean = false
        private set
    var isWon: Boolean = false
        private set

    init {
        setRoundSteps()
    }

    private fun setRoundSteps() {
        steps = generator.steps
    }

    fun start() {
        stepQueue.clear()
        stepQueue.addAll(steps!!)
        ledStripController.reset()
        isStarted = true
        isWon = false
    }

    internal fun reset() {
        setRoundSteps()
    }

    fun onPad(abcButton: AbcButton): Boolean {
        val step = stepQueue.poll()
        if (step != null) {
            val note = step.note
            if (notGuessed(abcButton, step)) {
                buzzerController.buzz(Note.MISS)
                return false
            } else {
                ledStripController.light(note.led)
                buzzerController.buzz(note)
                if (stepQueue.isEmpty()) {
                    isStarted = false
                    isWon = true
                }
                return true
            }
        } else {
            return false
        }
    }

    private fun notGuessed(abcButton: AbcButton, step: Step): Boolean {
        return step.abcButton != abcButton
    }

    internal fun onDestroy() {
        try {
            ledStripController.close()
            buzzerController.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
