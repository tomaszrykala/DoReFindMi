package com.tomaszrykala.dorefindmi.things.supplier.buzzer

import android.os.Handler
import android.os.HandlerThread

import com.google.android.things.contrib.driver.pwmspeaker.Speaker
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat

import java.io.IOException

class BuzzerSupplierImpl : BuzzerSupplier {

    private var speaker: Speaker? = null
    private val handler: Handler
    private val handlerThread: HandlerThread = HandlerThread("BuzzerSupplierImpl")

    init {
        handlerThread.start()
        handler = Handler(handlerThread.looper)
    }

    override fun init() {
        try {
            speaker = RainbowHat.openPiezo()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun play(pitch: Double) {
        handler.post {
            try {
                speaker!!.play(pitch)
                Thread.sleep(300)
                speaker!!.stop()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    @Throws(Exception::class)
    override fun close() {
        handlerThread.quit()
        speaker!!.close()
    }
}
