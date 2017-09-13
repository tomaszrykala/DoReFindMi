package com.tomaszrykala.dorefindmi.game

import com.tomaszrykala.dorefindmi.model.AbcButton
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController

class GameController(private val abcButtons: AbcButtonsController,
                     private val abcLeds: AbcLedsController,
                     private val digiDisplay: DigiDisplayController,
                     private val timer: Timer,
                     private val game: Game) : AbcButton.Listener {

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
        game.reset()
        startRound()
    }

    private fun showStarter() {
        val halfASecond = 500
        digiDisplay.displayBlocking("3...", halfASecond)
        digiDisplay.displayBlocking("2...", halfASecond)
        digiDisplay.displayBlocking("1...", halfASecond)
        digiDisplay.displayBlocking(" GO ", halfASecond)
    }

    private fun start() {
        abcButtons.setLastPressed(null)
        abcButtons.enable()
        abcLeds.reset()
        game.start()
    }

    private fun stop() {
        timer.stop()
        abcButtons.disable()
        digiDisplay.displayBlocking("WIN ", 2000)
        digiDisplay.display(digiDisplay.counter)
    }

    val isStarted: Boolean
        get() = game.isStarted

    val isWon: Boolean
        get() = game.isWon

    fun onDestroy() {
        try {
            abcButtons.close()
            abcLeds.close()
            digiDisplay.close()
            game.onDestroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
