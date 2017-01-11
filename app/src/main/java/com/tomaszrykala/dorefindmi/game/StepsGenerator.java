package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.game.generator.Generator;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.AbcButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.tomaszrykala.dorefindmi.model.Note.DO_HI;
import static com.tomaszrykala.dorefindmi.model.Note.DO_LO;
import static com.tomaszrykala.dorefindmi.model.Note.FA;
import static com.tomaszrykala.dorefindmi.model.Note.LA;
import static com.tomaszrykala.dorefindmi.model.Note.MI;
import static com.tomaszrykala.dorefindmi.model.Note.RE;
import static com.tomaszrykala.dorefindmi.model.Note.SI;
import static com.tomaszrykala.dorefindmi.model.Note.SO;
import static com.tomaszrykala.dorefindmi.model.AbcButton.A;
import static com.tomaszrykala.dorefindmi.model.AbcButton.B;
import static com.tomaszrykala.dorefindmi.model.AbcButton.C;

public class StepsGenerator {

    private final List<Note> notes = new ArrayList<>(Arrays.asList(
            new Note[]{DO_LO, RE, MI, FA, SO, LA, SI, DO_HI}));
    private final AbcButton[] abcButtons = new AbcButton[]{A, B, C};
    private final Generator generator;

    public StepsGenerator(Generator generator) {
        this.generator = generator;
    }

    public List<Step> steps() {
        return generator.getSteps(notes, abcButtons);
    }

}
