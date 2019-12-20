package interviewschedule.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DurationInterviewTest {

    @Test
    void givenDurationInterviewWithHoursInvolved_shouldConvertToDuration() {
        DurationInterview d = new DurationInterview("12:00", "13:00");
        assertEquals(Duration.ofHours(1), d.getDuration());
    }

    @Test
    void givenDurationInterviewWithHoursAndMinutesInvolved_shouldConvertToDuration() {
        DurationInterview d = new DurationInterview("12:00", "13:30");
        assertEquals(Duration.ofHours(1).plusMinutes(30), d.getDuration());
    }
}