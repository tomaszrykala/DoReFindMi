package com.tomaszrykala.dorefindmi.domain

enum class AbcButton(val value: String) {
    A("A"), B("B"), C("C");

    interface Listener {
        fun onAbcButton(abcButton: AbcButton)
    }
}
