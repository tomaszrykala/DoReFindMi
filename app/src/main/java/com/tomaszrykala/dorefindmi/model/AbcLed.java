package com.tomaszrykala.dorefindmi.model;

public enum AbcLed {
    A("A"), B("B"), C("C");

    public final String value;

    AbcLed(String value) {
        this.value = value;
    }

    @Override public String toString() {
        return value;
    }

    public interface Listener {
        void lightFor(AbcButton abcButton);

        void reset();
    }
}
