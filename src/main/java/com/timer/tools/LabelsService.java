package com.timer.tools;

import com.timer.model.TimeUnit;

public class LabelsService {

    public String getLabel(TimeUnit timeUnit, int value) {
        return switch (timeUnit) {
            case DAY -> "Дней";
            case HOUR -> "Часов";
            case MINUTE -> "Минут";
            case SECOND -> "Секунд";
        };
    }
}
