package com.tomaszrykala.dorefindmi.model;

public enum Gpio {
    A("A"), B("B"), C("C");

    public final String value;

    Gpio(String value) {
        this.value = value;
    }

    @Override public String toString() {
        return value;
    }
}
