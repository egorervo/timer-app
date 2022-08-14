package com.timer.tools;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TextPlacementUtil {

    public int getHeightTextVerticalOffset(int fontSize) {
        if (fontSize <= 10) {
            return 25;
        }
        if (fontSize >= 30) {
            return 20;
        }
        return fontSize;
    }
}
