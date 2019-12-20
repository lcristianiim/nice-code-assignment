package interviewschedule.schedule;

import org.junit.jupiter.api.Test;

import static interviewschedule.schedule.TimeAgoService.getDurationAsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeAgoServiceTest {

    @Test
    void givenDifferenceOfOneYear_ShouldReturnOneYearAgo() {
        String result = getDurationAsString("2018-10-26", "2019-10-26");

        assertEquals("1 year ago", result);
    }

    @Test
    void givenDifferenceOfTwoYears_ShouldReturnTwoYearsAgo() {
        String result = getDurationAsString("2017-01-26", "2019-10-26");

        assertEquals("2 years ago", result);
    }

    @Test
    void givenDifferenceOfOneMonth_ShouldReturnOneMonthAgo() {
        String result = getDurationAsString("2019-09-26", "2019-10-26");

        assertEquals("1 month ago", result);
    }

    @Test
    void givenDifferenceOfTwoMonths_ShouldReturnTwoMonthsAgo() {
        String result = getDurationAsString("2019-08-26", "2019-10-26");

        assertEquals("2 months ago", result);
    }
}