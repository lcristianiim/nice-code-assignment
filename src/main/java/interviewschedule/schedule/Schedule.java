package interviewschedule.schedule;

import interviewschedule.model.Candidate;
import interviewschedule.model.Interviewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface Schedule {
    Set<Candidate> getCandidates() throws IOException;
    Set<Interviewer> getInterviewers() throws IOException;
    Set<Candidate> getCandidatesByInterviewer(Interviewer interviewer) throws IOException;
    Duration getCompletedInterviewTimeSpentOn(Candidate candidate) throws IOException;
    List<List<String>> getScheduleForAllInterviewsInThePast(LocalDate date) throws IOException;
}
