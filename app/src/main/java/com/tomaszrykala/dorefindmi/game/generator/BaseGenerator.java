package com.tomaszrykala.dorefindmi.game.generator;

import com.tomaszrykala.dorefindmi.model.AbcButton;
import com.tomaszrykala.dorefindmi.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tomaszrykala.dorefindmi.model.AbcButton.A;
import static com.tomaszrykala.dorefindmi.model.AbcButton.B;
import static com.tomaszrykala.dorefindmi.model.AbcButton.C;
import static com.tomaszrykala.dorefindmi.model.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.model.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.model.Note.FA;
import static com.tomaszrykala.dorefindmi.model.Note.LA;
import static com.tomaszrykala.dorefindmi.model.Note.MI;
import static com.tomaszrykala.dorefindmi.model.Note.RE;
import static com.tomaszrykala.dorefindmi.model.Note.SI;
import static com.tomaszrykala.dorefindmi.model.Note.SO;

public abstract class BaseGenerator implements Generator {

    final List<Note> notes = new ArrayList<>(
            Arrays.asList(new Note[]{DO_LO, RE, MI, FA, SO, LA, SI, DO_HI})
    );

    final AbcButton[] abcButtons = new AbcButton[]{A, B, C};
}
