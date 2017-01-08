package com.tomaszrykala.dorefindmi;

class Buzzer {

    private Note lastBuzzed;

    public boolean lastBuzzedAt(Note note) {
        return lastBuzzed == note;
    }

    public void buzz(Note note) {
        lastBuzzed = note;
    }
}
