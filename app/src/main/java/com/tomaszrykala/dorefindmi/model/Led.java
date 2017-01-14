package com.tomaszrykala.dorefindmi.model;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public enum Led {
    ZERO(6, Color.YELLOW), ONE(5, Color.GREEN), TWO(4, Color.CYAN), THREE(3, Color.BLUE), FOUR(2, Color.LTGRAY),
    FIVE(1, Color.MAGENTA), SIX(0, Color.RED), ALL(7, Color.WHITE), NONE(-1, Color.BLACK);

    public final int index;
    @ColorInt public final int color;

    Led(int index, @ColorInt int color) {
        this.index = index;
        this.color = color;
    }
}
