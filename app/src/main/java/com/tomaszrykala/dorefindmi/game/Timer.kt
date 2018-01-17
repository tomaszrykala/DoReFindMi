package com.tomaszrykala.dorefindmi.game

import android.os.Handler
import android.os.Message

const val MSG = 1

class Timer(private val listener: Timer.Listener) {

    interface Listener {

        fun onTick(tick: Double)

        fun onStart()

        fun onStop()
    }

    private val handler = CountUpHandler(this)

    fun start() {
        reset()
        listener.onStart()
        handler.sendMessage(handler.obtainMessage(MSG))
    }

    fun stop() {
        listener.onStop()
        handler.removeMessages(MSG)
    }

    private fun onTick(elapsedTime: Double) {
        listener.onTick(elapsedTime)
    }

    private fun reset() {
        handler.reset()
    }

    class CountUpHandler(private val timer: Timer) : Handler() {

        private var base: Int = 0

        override fun handleMessage(msg: Message) {
            synchronized(this) {
                base++
                timer.onTick(base / 10.0)
                sendMessageDelayed(obtainMessage(MSG), 100)
            }
        }

        fun reset() {
            synchronized(this) {
                base = 0
            }
        }
    }
}
