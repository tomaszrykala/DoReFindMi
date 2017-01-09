package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.Note;

public class Buzzer {

    private Note lastBuzzed;

    public boolean lastBuzzedAt(Note note) {
        return lastBuzzed == note;
    }

    public void buzz(Note note) {
        lastBuzzed = note;
    }
}
