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

    // Android Things drivers
    private val abcButtonsSupplier = AbcButtonsSupplierImpl()
    private val abcLedsSupplier = AbcLedsSupplierImpl()
    private val digiDisplaySupplier = DigiDisplaySupplierImpl()
    private val ledStripSupplier = LedStripSupplierImpl()
    private val buzzerSupplier = BuzzerSupplierImpl()

    private var gameController: GameController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // driver Controllers
        val abcButtonsController = AbcButtonsController(abcButtonsSupplier)
        val abcLedsController = AbcLedsController(abcLedsSupplier)
        val digiDisplayController = DigiDisplayController(digiDisplaySupplier)
        val ledStripController = LedStripController(ledStripSupplier)
        val buzzerController = BuzzerController(buzzerSupplier)

        gameController = GameController(
                abcButtonsController,
                abcLedsController,
                digiDisplayController,
                Timer(digiDisplayController),
                Game(
                        ledStripController,
                        buzzerController,
                        GeneratorImpl()))
    }

    override fun onDestroy() {
        gameController!!.onDestroy()
        super.onDestroy()
    }
}
