package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.game.generator.Generator;
import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

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
import static com.tomaszrykala.dorefindmi.model.Pad.A;
import static com.tomaszrykala.dorefindmi.model.Pad.B;
import static com.tomaszrykala.dorefindmi.model.Pad.C;

public class StepsGenerator {

    private final List<Note> notes = new ArrayList<>(Arrays.asList(
            new Note[]{DO_LO, RE, MI, FA, SO, LA, SI, DO_HI}));
    private final Pad[] pads = new Pad[]{A, B, C};
    private final Generator generator;

    public StepsGenerator(Generator generator) {
        this.generator = generator;
    }

    public List<Step> steps() {
        return generator.getSteps(notes, pads);
    }

}
