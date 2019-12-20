package interviewschedule.schedule;

import interviewschedule.data.FileBasedDataRepo;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static interviewschedule.data.RowDataFetcher.*;

public class InterviewScheduleService {
    List<List<String>> records;
    FileBasedDataRepo repo = new FileBasedDataRepo();

    public InterviewScheduleService(List<List<String>> records) {
        this.records = records;
    }

    public List<List<String>> getInterviewsBeforeDate(LocalDate date) throws IOException {
        List<List<String>> result = new ArrayList<>();


        List<LocalDate> dates = repo.getDates()
                .stream()
                .filter(d -> isDateBeforeOrSame(d, date))
                .collect(Collectors.toList());

        List<Integer> indexes = getIndexesOfRowsWithBeforeDate(date, dates);
        indexes.forEach(i -> result.add(beautifyRow(i, date.toString())));

        Collections.sort(result, new Sorter<>());
        result.add(0, createHeader());

        return result;
    }

    private List<String> beautifyRow(int i, String originalDate) {
        List<String> result = new ArrayList<>();
        List<String> row = records.get(i);
        result.add(interviewerLastName(row.toArray(new String[0])));
        result.add(interviewerFirstName(row.toArray(new String[0])));
        result.add(interviewerTeam(row.toArray(new String[0])));
        result.add(candidateLastName(row.toArray(new String[0])));
        result.add(candidateFirstName(row.toArray(new String[0])));
        result.add(repo.getEnhancedRole(candidateRole(row.toArray(new String[0]))));
        result.add(interviewType(row.toArray(new String[0])));
        result.add(TimeAgoService.getDurationAsString(interviewDate(row.toArray(new String[0])), originalDate));

        return result;
    }

    private List<Integer> getIndexesOfRowsWithBeforeDate(LocalDate date, List<LocalDate> dates) {
        List<Integer> indexes = new ArrayList<>();

        for (int i = 0; i < dates.size(); i++) {
            if (isDateBeforeOrSame(dates.get(i), date))
                indexes.add(i);
        }
        return indexes;
    }

    private boolean isDateBeforeOrSame(LocalDate d, LocalDate date) {
//        if (d.isEqual(date))
//            return true;
        return d.isBefore(date);
    }

    private List<String> createHeader() {
        return Arrays.asList(
                    "Interviewer last name",
                    "Interviewer first name",
                    "Interviewer team",
                    "Candidate last name",
                    "Candidate first name",
                    "Candidate role",
                    "Interview type",
                    "Interview time ago");
    }
}
