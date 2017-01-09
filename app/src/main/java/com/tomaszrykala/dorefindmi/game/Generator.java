package com.tomaszrykala.dorefindmi.game;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.util.List;

interface Generator {

    @NonNull List<Step> getSteps(List<Note> notes, Pad[] pads);
}
