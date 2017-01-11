package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.AbcButton;

public class Step {

    private final AbcButton abcButton;
    private final Note note;

    public Step(AbcButton abcButton, Note note) {
        this.abcButton = abcButton;
        this.note = note;
    }

    public AbcButton getAbcButton() {
        return abcButton;
    }

    public Note getNote() {
        return note;
    }
}
