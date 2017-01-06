package com.tomaszrykala.dorefindmi;

class Step {
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
