package com.tomaszrykala.dorefindmi.game;

import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.model.Pad;

public class Step {

    private final Pad pad;
    private final Note note;

    public Step(Pad pad, Note note) {
        this.pad = pad;
        this.note = note;
    }

    public Pad getPad() {
        return pad;
    }

    public Note getNote() {
        return note;
    }
}
