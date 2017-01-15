package com.tomaszrykala.dorefindmi.game.generator;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Step;

import java.util.List;

interface Generator {

    @NonNull List<Step> getSteps();
}
