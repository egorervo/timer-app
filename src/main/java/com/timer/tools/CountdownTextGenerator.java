package com.timer.tools;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CountdownTextGenerator {

    public List<String> getCountDownText(LocalDateTime endTime,
                                         int imagesCount,
                                         boolean includeDays,
                                         String separatorText) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(endTime)) {
            return List.of(includeDays ?
                    "00%s00%s00%s00".formatted(separatorText, separatorText, separatorText, separatorText)
                    : "00%s00%s00".formatted(separatorText, separatorText, separatorText));
        }
        List<String> res = new ArrayList<>();
        Duration between = Duration.between(now, endTime);

        while (!between.isNegative() && imagesCount != 0) {
            String format = includeDays ? "dd%sHH%smm%sss".formatted(separatorText, separatorText, separatorText, separatorText)
                    : "HH%smm%sss".formatted(separatorText, separatorText, separatorText);
            res.add(DurationFormatUtils.formatDuration(between.toMillis(), format));
            imagesCount --;
            between = between.minus(1, ChronoUnit.SECONDS);
        }
        return res;
    }
}
