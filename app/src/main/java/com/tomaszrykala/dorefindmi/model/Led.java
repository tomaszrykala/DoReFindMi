package com.tomaszrykala.dorefindmi.model;

public enum Led {
    ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), ALL("ALL"), NONE(null);

    public final String value;

    Led(String value) {
        this.value = value;
    }

    @Override public String toString() {
        return value;
    }
}
