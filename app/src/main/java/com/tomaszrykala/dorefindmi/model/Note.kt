package com.tomaszrykala.dorefindmi.model

enum class Note constructor(val led: Led, val order: Int, val pitch: Double) {
    DO_LO(Led.ZERO, 0, 261.626), RE(Led.ONE, 1, 293.665), MI(Led.TWO, 2, 329.628),
    FA(Led.THREE, 3, 349.228), SO(Led.FOUR, 4, 391.995), LA(Led.FIVE, 5, 440.0),
    SI(Led.SIX, 6, 493.883), DO_HI(Led.ALL, 7, 523.251), MISS(Led.NONE, -1, 110.0)
}
