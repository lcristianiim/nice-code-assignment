package interviewschedule.schedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeAgoService {

    public static String getDurationAsString(String compareDate, String originalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate cd = LocalDate.parse(compareDate, formatter);
        LocalDate od = LocalDate.parse(originalDate, formatter);

        int difference = od.getYear() - cd.getYear();
        String result = evaluateDifference(difference, "year", "years");

        if (result == null) {
            difference = od.getMonthValue() - cd.getMonthValue();
            result = evaluateDifference(difference, "month", "months");
        }

        if (result == null) {
            difference = od.getDayOfMonth() - cd.getDayOfMonth();
            if (difference >= 7)
                result = evaluateDifference(difference / 7, "week", "weeks");
            if (difference < 7)
                result = evaluateDifference(difference, "day", "days");
        }

        return result;
    }

    private static String evaluateDifference(int difference, String singular, String plural) {
        if (difference == 1)
            return difference + " " + singular + " ago";

        if (difference > 0)
            return difference + " " + plural + " ago";
        return null;
    }
}
