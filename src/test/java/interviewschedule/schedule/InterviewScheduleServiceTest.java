package interviewschedule.schedule;

import interviewschedule.data.FileBasedDataRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InterviewScheduleServiceTest {
    FileBasedDataRepo repo = new FileBasedDataRepo();
    LocalDate date = LocalDate.of(2019,10,26);

    @Test
    void shouldAddHeader_givenEmptySchedulesList() throws IOException {
        String[] header = {"Interviewer last name", "Interviewer first name", "Interviewer team", "Candidate last name", "Candidate first name", "Candidate role", "Interview type", "Interview time ago"};
        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList(header));

        LocalDate date = LocalDate.now();


        InterviewScheduleService iss = new InterviewScheduleService(repo.getRawRows());

        List<List<String>> result = iss.getInterviewsBeforeDate(LocalDate.of(1,1,1));

        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldAddHeaderAtFirstPosition_givenNotEmptySchedulesList() throws IOException {
        List<String> header = Arrays.asList(
                "Interviewer last name",
                "Interviewer first name",
                "Interviewer team",
                "Candidate last name",
                "Candidate first name",
                "Candidate role",
                "Interview type",
                "Interview time ago"
        );
        List<List<String>> expected = new ArrayList<>();
        expected.add(header);

        LocalDate date = LocalDate.now();

        List<List<String>> records = repo.getRawRows();
        InterviewScheduleService iss = new InterviewScheduleService(records);

        List<List<String>> result = iss.getInterviewsBeforeDate(date);

        Assertions.assertEquals(expected.get(0), result.get(0));
        Assertions.assertEquals(records.size() + 1, result.size());
    }

    @Test
    void shouldBeautifyRawRow() throws IOException {
        List<String> expected = Arrays.asList(
                "Altamirano",
                "Alleen",
                "A-team",
                "Lesher",
                "Liz",
                "Business Analyst",
                "Telefonic",
                "1 day ago"
        );
        List<List<String>> records = new ArrayList<>();

        InterviewScheduleService iss = new InterviewScheduleService(repo.getRawRows());
        List<List<String>> result = iss.getInterviewsBeforeDate(date);

        Assertions.assertEquals(expected, result.get(2));
    }
}