package com.tomaszrykala.dorefindmi.things.controller.buzzer;

import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.supplier.buzzer.BuzzerSupplier;

public class BuzzerController implements BaseController {

    private Note lastBuzzed;
    private final BuzzerSupplier supplier;

    public BuzzerController(BuzzerSupplier buzzerSupplier) {
        supplier = buzzerSupplier;
        supplier.init();
    }

    public boolean lastBuzzedAt(Note note) {
        return lastBuzzed == note;
    }

    public void buzz(Note note) {
        supplier.play(note.pitch);
        lastBuzzed = note;
    }

    @Override public void close() throws Exception {
        supplier.close();
    }

}
