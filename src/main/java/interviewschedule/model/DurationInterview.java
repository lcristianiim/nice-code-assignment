package interviewschedule.model;

import java.time.Duration;
import java.time.LocalTime;

public class DurationInterview {
    private String startTime;
    private String endTime;

    public DurationInterview(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Duration getDuration() {
        return Duration.between(LocalTime.parse(startTime), LocalTime.parse(endTime));
    }
}