package com.tomaszrykala.dorefindmi.things.suppliers.buzzer;

import com.google.android.things.contrib.driver.pwmspeaker.Speaker;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;

import java.io.IOException;

public class RealBuzzerSupplier implements BuzzerSupplier {

    private Speaker speaker;

    public RealBuzzerSupplier() {
        init();
    }

    @Override
    public void init() {
        try {
            speaker = RainbowHat.openPiezo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void play(double pitch) {
        try {
            speaker.play(pitch); // 440
            Thread.sleep(500);
            speaker.stop();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override public void close() throws Exception {
        speaker.close();
    }
}
