package com.tomaszrykala.dorefindmi.things.supplier.buzzer

import android.os.Handler
import android.os.HandlerThread

import com.google.android.things.contrib.driver.pwmspeaker.Speaker
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat

import java.io.IOException

class BuzzerSupplierImpl : BuzzerSupplier {

    private val speaker: Speaker by lazy { RainbowHat.openPiezo() }

    private val handler: Handler by lazy {
        handlerThread.start()
        Handler(handlerThread.looper)
    }

    private val handlerThread: HandlerThread = HandlerThread("BuzzerSupplierImpl")

    override fun play(pitch: Double) {
        handler.post {
            try {
                speaker.play(pitch)
                Thread.sleep(300)
                speaker.stop()
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
        speaker.close()
    }
}
