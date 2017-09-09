package com.tomaszrykala.dorefindmi

import android.app.Activity
import android.os.Bundle
import com.tomaszrykala.dorefindmi.game.Game
import com.tomaszrykala.dorefindmi.game.GameController
import com.tomaszrykala.dorefindmi.game.Timer
import com.tomaszrykala.dorefindmi.game.generator.GeneratorImpl
import com.tomaszrykala.dorefindmi.things.controller.abcbuttons.AbcButtonsController
import com.tomaszrykala.dorefindmi.things.controller.abcleds.AbcLedsController
import com.tomaszrykala.dorefindmi.things.controller.buzzer.BuzzerController
import com.tomaszrykala.dorefindmi.things.controller.digidisplay.DigiDisplayController
import com.tomaszrykala.dorefindmi.things.controller.ledstrip.LedStripController
import com.tomaszrykala.dorefindmi.things.supplier.abcbuttons.AbcButtonsSupplierImpl
import com.tomaszrykala.dorefindmi.things.supplier.abcleds.AbcLedsSupplierImpl
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.BuzzerSupplierImpl
import com.tomaszrykala.dorefindmi.things.supplier.digidisplay.DigiDisplaySupplierImpl
import com.tomaszrykala.dorefindmi.things.supplier.ledstrip.LedStripSupplierImpl

class DoReFindMiActivity : Activity() {

    private val abcButtonsController = AbcButtonsController(AbcButtonsSupplierImpl())
    private val abcLedsController = AbcLedsController(AbcLedsSupplierImpl())
    private val digiDisplayController = DigiDisplayController(DigiDisplaySupplierImpl())
    private val ledStripController = LedStripController(LedStripSupplierImpl())
    private val buzzerController = BuzzerController(BuzzerSupplierImpl())

    private val gameController: GameController by lazy {
        GameController(
                abcButtonsController,
                abcLedsController,
                digiDisplayController,
                Timer(digiDisplayController),
                Game(ledStripController, buzzerController, GeneratorImpl()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameController.startRound()
    }

    override fun onDestroy() {
        gameController.onDestroy()
        super.onDestroy()
    }
}
