package com.tomaszrykala.dorefindmi.game;

import android.os.Handler;
import android.os.Message;

import static com.tomaszrykala.dorefindmi.game.Timer.CountUpHandler.MSG;

public class Timer {

    public interface Listener {

        void onTick(double tick);

        void onStart();

        void onStop();
    }

    private final CountUpHandler handler = new CountUpHandler(this);

    private final Listener listener;

    public Timer(Listener listener) {
        this.listener = listener;
    }

    void start() {
        reset();
        listener.onStart();
        handler.sendMessage(handler.obtainMessage(MSG));
    }

    void stop() {
        listener.onStop();
        handler.removeMessages(MSG);
    }

    private void onTick(double elapsedTime) {
        listener.onTick(elapsedTime);
    }

    private void reset() {
        handler.reset();
    }

    static class CountUpHandler extends Handler {

        static final int MSG = 1;

        private int base;
        private final Timer timer;

        CountUpHandler(Timer timer) {
            this.timer = timer;
        }

        @Override public void handleMessage(Message msg) {
            synchronized (this) {
                base++;
                timer.onTick(base / 10.0);
                sendMessageDelayed(obtainMessage(MSG), 100);
            }
        }

        void reset() {
            synchronized (this) {
                base = 0;
            }
        }
    }
}
