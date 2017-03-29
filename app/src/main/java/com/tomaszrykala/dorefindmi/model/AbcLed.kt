package com.tomaszrykala.dorefindmi.model

enum class AbcLed constructor(val value: String) {
    A("A"), B("B"), C("C");

    interface Listener {
        fun lightFor(abcButton: AbcButton)

        fun reset()
    }
}
