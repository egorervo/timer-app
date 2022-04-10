package com.timer.model;

import com.timer.tools.ImageCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageDimensions {
    private Dimensions separatorDimensions;
    private Dimensions daysDimensions;
    private Dimensions hoursDimensions;
    private Dimensions minutesDimensions;
    private Dimensions secondsDimensions;

    private Dimensions daysLabelDimensions;
    private Dimensions hoursLabelDimensions;
    private Dimensions minutesLabelDimensions;
    private Dimensions secondsLabelDimensions;
}
