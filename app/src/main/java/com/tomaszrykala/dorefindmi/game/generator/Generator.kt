package com.tomaszrykala.dorefindmi.game.generator

import com.tomaszrykala.dorefindmi.domain.Step

interface Generator {

    val steps: List<Step>
}
