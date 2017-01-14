package com.tomaszrykala.dorefindmi.model;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public enum Led {
    ZERO(0, Color.YELLOW), ONE(1, Color.GREEN), TWO(2, Color.CYAN), THREE(3, Color.BLUE), FOUR(4, Color.LTGRAY),
    FIVE(5, Color.MAGENTA), SIX(6, Color.RED), ALL(7, Color.WHITE), NONE(-1, Color.BLACK);

    public final int index;
    @ColorInt public final int color;

    Led(int index, @ColorInt int color) {
        this.index = index;
        this.color = color;
    }
}
