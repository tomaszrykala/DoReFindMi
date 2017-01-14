package com.tomaszrykala.dorefindmi.things;

import com.tomaszrykala.dorefindmi.model.Note;
import com.tomaszrykala.dorefindmi.things.controller.BaseController;
import com.tomaszrykala.dorefindmi.things.suppliers.buzzer.BuzzerSupplier;

public class Buzzer implements BaseController {

    private Note lastBuzzed;
    private final BuzzerSupplier supplier;

    public Buzzer(BuzzerSupplier supplier) {
        this.supplier = supplier;
    }

    public boolean lastBuzzedAt(Note note) {
        return lastBuzzed == note;
    }

    /**
     * Do 3: 261,626
     * Do# 3: 277,183
     * Re 3: 293,665
     * Re# 3: 311,127
     * Mi 3: 329,628
     * Fa 3: 349,228
     * Fa# 3: 369,994
     * Sol 3: 391,995
     * Sol#3: 415,305
     * La 3: 440
     * La# 3: 466,164
     * Si 3: 493,883
     * <p>
     * Do 4: 523,251
     * <p>
     * https://es.answers.yahoo.com/question/index?qid=20100425131855AAnc6FZ
     */
    public void buzz(Note note) {
        lastBuzzed = note;

        switch (note) {
            case DO_LO:
                supplier.play(261.626);
                break;
            case RE:
                supplier.play(293.665);
                break;
            case MI:
                supplier.play(329.628);
                break;
            case FA:
                supplier.play(349.228);
                break;
            case SO:
                supplier.play(391.995);
                break;
            case LA:
                supplier.play(440);
                break;
            case SI:
                supplier.play(493.883);
                break;
            case DO_HI:
                supplier.play(523.251);
                break;
            case MISS:
                supplier.play(110);
                supplier.play(110);
                break;
        }
    }

    @Override public void close() throws Exception {
        supplier.close();
    }

}
