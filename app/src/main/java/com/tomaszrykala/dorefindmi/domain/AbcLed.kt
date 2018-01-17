package com.tomaszrykala.dorefindmi.domain

enum class AbcLed {
    A, B, C;

    interface Listener {
        fun lightFor(abcButton: AbcButton)

        fun reset()
    }
}
