package com.tomaszrykala.dorefindmi.game

import com.tomaszrykala.dorefindmi.domain.AbcButton
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController
import java.util.concurrent.TimeUnit

const val HALF_A_SECOND_IN_MILLIS = 500

class GameController(private val abcButtons: AbcButtonsController,
                     private val abcLeds: AbcLedsController,
                     private val digiDisplay: DigiDisplayController,
                     private val timer: Timer,
                     private val game: Game) : AbcButton.Listener {

    private val closeables: List<AutoCloseable> = listOf(abcButtons, abcLeds, digiDisplay)

    val isStarted: Boolean
        get() = game.isStarted

    val isWon: Boolean
        get() = game.isWon

    init {
        abcButtons.setListener(this)
    }

    fun startRound() {
        showStarter()
        timer.start()
        start()
    }

    override fun onAbcButton(abcButton: AbcButton) {
        if (!game.isStarted) {
            startNewRound()
        } else {
            val onPad = game.onPad(abcButton)
            abcButtons.setLastPressed(abcButton)
            if (!onPad) {
                start()
            } else {
                abcLeds.lightFor(abcButton)
                if (game.isWon) {
                    stop()
                }
            }
        }
    }

    private fun startNewRound() {
        startRound()
    }

    private fun showStarter() {
        digiDisplay.apply {
            (3 downTo 1).forEach {
                displayBlocking(String.format("%d...", it), HALF_A_SECOND_IN_MILLIS)
            }.also {
                displayBlocking(" GO ", HALF_A_SECOND_IN_MILLIS)
            }
        }
    }

    private fun start() {
        abcButtons.apply {
            setLastPressed(null)
            enable()
        }
        abcLeds.reset()
        game.start()
    }

    private fun stop() {
        timer.stop()
        abcButtons.disable()
        digiDisplay.apply {
            displayBlocking("WIN ", TimeUnit.SECONDS.toMillis(2).toInt())
            display(digiDisplay.counter)
        }
    }

    fun onDestroy() {
        try {
            closeables.forEach { it.close() }
            game.onDestroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
