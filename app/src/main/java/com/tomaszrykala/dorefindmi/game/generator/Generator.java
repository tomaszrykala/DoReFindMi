package com.tomaszrykala.dorefindmi.game.generator;

import android.support.annotation.NonNull;

import com.tomaszrykala.dorefindmi.game.Step;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

import java.util.List;

public interface Generator {

    @NonNull List<Step> getSteps(List<Note> notes, Pad[] pads);
}
