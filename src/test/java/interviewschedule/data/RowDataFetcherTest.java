package interviewschedule.data;

import org.junit.jupiter.api.Test;

import static interviewschedule.data.RowDataFetcher.*;
import static org.junit.jupiter.api.Assertions.*;

class RowDataFetcherTest {
    private String[] row = {
            "Anita", "Austell", "A-team", "Keira", "Kravitz", "DEV", "HR", "2019-05-17", "13:45", "15:00", "Yes"};

    @Test
    void shouldFetchCandidateFirstName_givenRow() {
        assertEquals("Keira", candidateFirstName(row));
    }
    @Test
    void shouldFetchCandidateLastName_givenRow() {
        assertEquals("Kravitz", candidateLastName(row));

    }
    @Test
    void shouldFetchCandidateRole_givenRow() {
        assertEquals("DEV", candidateRole(row));
    }
    @Test
    void shouldFetchInterviewerFirstName_givenRow() {
        assertEquals("Anita", interviewerFirstName(row));
    }
    @Test
    void shouldFetchInterviewerLastName_givenRow() {
        assertEquals("Austell", interviewerLastName(row));
    }
    @Test
    void shouldFetchInterviewerTeam_givenRow() {
        assertEquals("A-team", interviewerTeam(row));
    }
    @Test
    void shouldFetchInterviewStartTime_givenRow() {
        assertEquals("13:45", interviewStartTime(row));
    }
    @Test
    void shouldFetchInterviewEndTime_givenRow() {
        assertEquals("15:00", interviewEndTime(row));
    }
    @Test
    void shouldFetchIsInterviewState_givenRow() {
        assertEquals("Yes", interviewState(row));
    }
}