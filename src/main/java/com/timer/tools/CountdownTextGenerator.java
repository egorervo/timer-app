package com.timer.tools;

import com.timer.model.ImageTextParams;
import com.timer.model.TimeUnit;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CountdownTextGenerator {
    private final LabelsService labelsService = new LabelsService();

    public List<ImageTextParams> getCountDownTextParams(LocalDateTime endTime,
                                                        int imagesCount,
                                                        boolean includeDays,
                                                        boolean includeLabels,
                                                        String separatorText) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(endTime)) {
            return List.of(ImageTextParams.builder()
                    .days("00")
                    .minutes("00")
                    .hours("00")
                    .seconds("00")
                    .labelDays(labelsService.getLabel(TimeUnit.DAY, 0))
                    .labelHours(labelsService.getLabel(TimeUnit.HOUR, 0))
                    .labelMinute(labelsService.getLabel(TimeUnit.MINUTE, 0))
                    .labelSeconds(labelsService.getLabel(TimeUnit.SECOND, 0))
                    .separator(separatorText)
                    .includeDays(includeDays)
                    .includeLabels(includeLabels)
                    .build());
        }
        List<ImageTextParams> res = new ArrayList<>();
        Duration between = Duration.between(now, endTime);

        while (!between.isNegative() && imagesCount != 0) {
            String timeString = DurationFormatUtils.formatDuration(between.toMillis(), includeDays ? "dd:HH:mm:ss" : "HH:mm:ss");
            String[] split = timeString.split(":");
            String days = includeDays ? split[0] : "";
            String hours = split[includeDays ? 1 : 0];
            String minutes = split[includeDays ? 2 : 1];;
            String seconds = split[includeDays ? 3 : 2];


            res.add(ImageTextParams.builder()
                    .days(days)
                    .hours(hours)
                    .minutes(minutes)
                    .seconds(seconds)
                    .labelDays(includeDays ? labelsService.getLabel(TimeUnit.DAY, Integer.valueOf(days)) : "")
                    .labelHours(labelsService.getLabel(TimeUnit.HOUR, Integer.valueOf(hours)))
                    .labelMinute(labelsService.getLabel(TimeUnit.MINUTE, Integer.valueOf(minutes)))
                    .labelSeconds(labelsService.getLabel(TimeUnit.SECOND, Integer.valueOf(seconds)))
                    .separator(separatorText)
                    .includeDays(includeDays)
                    .includeLabels(includeLabels)
                    .build());
            imagesCount --;
            between = between.minus(1, ChronoUnit.SECONDS);
        }
        return res;
    }
}
