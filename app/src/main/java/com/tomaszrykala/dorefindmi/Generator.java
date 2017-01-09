package com.tomaszrykala.dorefindmi;

import android.support.annotation.NonNull;

import java.util.List;

interface Generator {

    @NonNull List<Step> getSteps(List<Note> notes, Pad[] pads);
}
