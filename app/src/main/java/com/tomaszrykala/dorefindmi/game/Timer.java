package com.tomaszrykala.dorefindmi.game;

import android.os.Handler;
import android.os.Message;

public class Timer {

    public interface Listener {

        void onTick(double tick);

        void onStart();

        void onStop();
    }

    private final CountUpTimer countUpTimer;

    public Timer(Listener listener) {
        countUpTimer = new CountUpTimer(100, listener);
    }

    void start() {
        countUpTimer.start();
    }

    void stop() {
        countUpTimer.stop();
    }

    private static class CountUpTimer {

        private static final int MSG = 1;

        private final Listener listener;

        private int base;
        private final int interval;
        private Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                synchronized (CountUpTimer.this) {
                    base++;
                    onTick(base / 10.0);
                    sendMessageDelayed(obtainMessage(MSG), interval);
                }
            }
        };

        CountUpTimer(int interval, Listener listener) {
            this.interval = interval;
            this.listener = listener;
        }

        void onTick(double elapsedTime) {
            listener.onTick(elapsedTime);
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

        void reset() {
            synchronized (this) {
                base = 0;
            }
        }
    }
}
