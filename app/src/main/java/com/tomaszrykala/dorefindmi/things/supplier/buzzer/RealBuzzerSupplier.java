package com.tomaszrykala.dorefindmi.things.supplier.buzzer;

import android.os.Handler;
import android.os.HandlerThread;

import com.google.android.things.contrib.driver.pwmspeaker.Speaker;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;

import java.io.IOException;

public class RealBuzzerSupplier implements BuzzerSupplier {

    private Speaker speaker;
    private Handler handler;
    private final HandlerThread handlerThread;

    public RealBuzzerSupplier() {
        handlerThread = new HandlerThread("RealBuzzerSupplier");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
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
    public void play(final double pitch) {
        handler.post(new Runnable() {
            @Override public void run() {
                try {
                    speaker.play(pitch);
                    Thread.sleep(300);
                    speaker.stop();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void close() throws Exception {
        handlerThread.quit();
        speaker.close();
    }
}
