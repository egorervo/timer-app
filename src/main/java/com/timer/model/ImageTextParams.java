package com.timer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageTextParams {
    private String days;
    private String hours;
    private String minutes;
    private String seconds;
    private String labelDays;
    private String labelHours;
    private String labelMinute;
    private String labelSeconds;
    private String separator;
    private boolean includeDays;
    private boolean includeLabels;
}
