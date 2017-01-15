package com.tomaszrykala.dorefindmi.model;

public enum AbcButton {
    A("A"), B("B"), C("C");
    public final String value;

    AbcButton(String value) {
        this.value = value;
    }

    public interface Listener {
        void onAbcButton(AbcButton abcButton);
    }
}
