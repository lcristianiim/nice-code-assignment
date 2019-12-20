package interviewschedule.data;

import interviewschedule.model.*;
import interviewschedule.schedule.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static interviewschedule.data.RowDataFetcher.*;

public class FileBasedDataRepo implements Repository {

    @Override
    public Set<Candidate> getCandidates() throws IOException {
        List<String[]> data = readFileData();

        return data.stream()
                .map(this::createCandidateFromData)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Interviewer> getInterviewers() throws IOException {
        List<String[]> data = readFileData();

        return data.stream()
                .map(this::createInterviewerFromData)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Candidate> getCandidatesByInterviewer(Interviewer interviewer) throws IOException {
        List<String[]> data = readFileData();
        return data.stream()
                .filter(row -> isInterviewerPresentInRow(interviewer, row))
                .map(this::getCandidateFromRow)
                .collect(Collectors.toSet());

    }

    @Override
    public List<DurationInterview> getCompletedDurationsInterviewsOfCandidate(Candidate candidate) throws IOException {
        List<String[]> data = readFileData();

        return data.stream()
                .filter(row -> isCandidatePresentInRow(candidate, row))
                .filter(row -> isInterviewerCompleted(interviewState(row)))
                .map(r -> new DurationInterview(interviewStartTime(r), interviewEndTime(r)))
                .collect(Collectors.toList());
    }

    @Override
    public List<List<String>> getRawRows() throws IOException {
        List<String[]> data = readFileData();
        return data.stream()
                .map(Arrays::asList)
                .collect(Collectors.toList());
    }
    public List<LocalDate> getDates() throws IOException {
        List<String[]> data = readFileData();

        return data.stream()
                .map(RowDataFetcher::interviewDate)
                .map(this::convertToLocalDateTime)
                .collect(Collectors.toList());

    }

    private LocalDate convertToLocalDateTime(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(s, formatter);
    }

    public boolean isInterviewerCompleted(String state) {
         return "Yes".equals(state);
    }

    private List<String[]> readFileData() throws IOException {
        List<String[]> data = new ArrayList<>();
        File file =  new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("test-schedule.tsv")).getFile() );
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            data.add(st.split("\\s+"));
        data.remove(0);
        return data;
    }

    private Candidate createCandidateFromData(String[] row) {
        return new CandidateBuilder().build(candidateLastName(row), candidateFirstName(row), getCandidateRole(row));
    }

    private String getCandidateRole(String[] row) {
        return getEnhancedRole(candidateRole(row));
    }

    public String getEnhancedRole(String rawRole) {
        if (rawRole.equals("DEV"))
            return "Developer";
        if (rawRole.equals("QA"))
            return "Quality Assurance";
        if (rawRole.equals("BA"))
            return "Business Analyst";

        return rawRole;
    }

    private Interviewer createInterviewerFromData(String[] row) {
        return new InterviewerBuilder().build(interviewerLastName(row), interviewerFirstName(row), interviewerTeam(row));
    }

    private boolean isInterviewerPresentInRow(Interviewer interviewer, String[] row) {
        String interviewerFromRow = interviewerFirstName(row) + interviewerLastName(row) + interviewerTeam(row);
        return interviewer.toString().equals(interviewerFromRow);
    }

    private Candidate getCandidateFromRow(String[] row) {
        return new CandidateBuilder().build(
                candidateLastName(row),
                candidateFirstName(row),
                getEnhancedRole(candidateRole(row)));
    }

    private boolean isCandidatePresentInRow(Candidate candidate, String[] row) {
        String candidateFomRow = candidateFirstName(row) + candidateLastName(row) + getEnhancedRole(candidateRole(row));
        return candidate.toString().equals(candidateFomRow);
    }

}
