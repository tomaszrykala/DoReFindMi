package com.tomaszrykala.dorefindmi.model

enum class AbcLed {
    A, B, C;

    interface Listener {
        fun lightFor(abcButton: AbcButton)

        fun reset()
    }
}
